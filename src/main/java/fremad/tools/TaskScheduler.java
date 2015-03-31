package fremad.tools;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fremad.domain.LeagueObject;
import fremad.domain.MatchObject;
import fremad.domain.TableEntryObject;
import fremad.domain.TeamObject;
import fremad.domain.list.LeagueListObject;
import fremad.domain.list.MatchListObject;
import fremad.domain.list.TableEntryListObject;
import fremad.service.LeagueService;
import fremad.service.MatchService;
import fremad.service.TableEntryService;
import fremad.service.TeamService;
import fremad.utils.UrlParser;

@Component
@Scope("singleton")
public class TaskScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(TaskScheduler.class);
	private static final int updateFrequency = 1000 * 60 * 60 * 3;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	TableEntryService tableEntryService;
	
	@Autowired
	LeagueService leagueService;

		
	@Scheduled(fixedRate = updateFrequency)
	public void updateFixtureTask() {
		LOG.debug("In updateFixtureTask");
		
		LeagueListObject leagueList = leagueService.getLeaguesByYear(Calendar.getInstance().get(Calendar.YEAR));
		
		LOG.debug("Found " + leagueList.size() + " leagues");
		
		int matchesAdded = 0;
		for (LeagueObject league : leagueList.getList()) {
			TeamObject team = teamService.getTeam(league.getTeam());
			MatchListObject matches = UrlParser.getMatchListObject(team, league, false);
			MatchListObject existingMatches = matchService.getMatches(league.getTeam());
			matches = fillIdForExistingMatches (matches, existingMatches);
			LOG.debug("Found " + matches.size() + " matches for team " + league.getTeam());
			for (MatchObject match : matches) {
				if(addMatchIfLeague(league, match)){
					matchesAdded++;
				}
			}
		}
		LOG.debug("Updated or added " + matchesAdded + " matches");
		checkIfOldMatchesIsEmpty();
		
	}

	@Scheduled(fixedRate = updateFrequency)
	public void updateTableTask() {
		LOG.debug("In updateTableTask");
		
		LeagueListObject leagueList = leagueService.getLeaguesByYear(Calendar.getInstance().get(Calendar.YEAR));
		
		LOG.debug("Found " + leagueList.size() + " leagues");
		
		int tableEntriesAdded = 0;
		for (LeagueObject league : leagueList.getList()) {
			TableEntryListObject tableEntries = UrlParser.getTableEntryListObject(league, false);
			TableEntryListObject existingTableEntries = tableEntryService.getTableEntries(league.getId());
			tableEntries = fillIdForExistingTableEntries(tableEntries, existingTableEntries);
			LOG.debug("Found " + tableEntries.size() + " tableEntries for league " + league.getId());
			for (TableEntryObject tableEntry : tableEntries) {
				if (addOrUpdateTableEntry(tableEntry)) {
					tableEntriesAdded++;
				} else {
					LOG.error("Failed to add match with leagueId " + tableEntry.getLeagueId());
				}
			}
		}
		LOG.debug("Updated or added " + tableEntriesAdded + " tableEntries");
		checkIfOldTableIsEmpty();
	}
	
	private MatchListObject fillIdForExistingMatches(MatchListObject matches, MatchListObject existingMatches){
		for (MatchObject match : matches) {
			match.setId(-1);
			for (MatchObject existingMatch : existingMatches) {
				if(match.getLeague() == existingMatch.getLeague() &&
						match.getOpposingTeamId() == existingMatch.getOpposingTeamId() &&
						match.isHomeMatch() == existingMatch.isHomeMatch()){
					match.setId(existingMatch.getId());
				}
			}
		}
		return matches;
	}
	
	private TableEntryListObject fillIdForExistingTableEntries(TableEntryListObject tableEntries, TableEntryListObject existingTableEntries){
		for (TableEntryObject tableEntry : tableEntries) {
			tableEntry.setId(-1);
			for (TableEntryObject existingTableEntry : existingTableEntries) {
				LOG.debug("Ser etter denne matchen:"
						+ " tableEntry.getLeagueId() == " + tableEntry.getLeagueId() 
						+ " existingTableEntry.getLeagueId() == " + existingTableEntry.getLeagueId()
						+ " tableEntry.getLeagueId() == " + tableEntry.getTeamId()
						+ " existingTableEntry.getLeagueId() == " + existingTableEntry.getTeamId()
						+ " tableEntry.getLeagueId() == " + tableEntry.getTeamName()
						+ " existingTableEntry.getLeagueId() == " + existingTableEntry.getTeamName());
				if(tableEntry.getLeagueId() == existingTableEntry.getLeagueId() &&
						tableEntry.getTeamId() == existingTableEntry.getTeamId()){
					tableEntry.setId(existingTableEntry.getId());
					break;
				}
			}
		}
		return tableEntries;
	}
	
	private boolean addMatchIfLeague(LeagueObject league, MatchObject match){
		if(league.getId() == match.getLeague()){
			return addOrUpdateMatch(match);
		}
		LOG.info("Did not add match with leagueId " + match.getLeague());
		return false;
	}
	
	private boolean addOrUpdateMatch(MatchObject match){
		if(match.getId() == -1){
			return matchService.addMatch(match);
		}else{
			return matchService.updateMatch(match);
		}
	}
	
	private boolean addOrUpdateTableEntry(TableEntryObject tableEntry){
		if(tableEntry.getId() == -1){
			return tableEntryService.addTableEntry(tableEntry);
		}else{
			return tableEntryService.updateTableEntry(tableEntry);
		}
	}
	
	private void checkIfOldTableIsEmpty(){
		LOG.debug("In checkIfOldTableIsEmpty");
		
		LeagueListObject leagueList = leagueService.getLeagues();
		
		LOG.debug("Found " + leagueList.size() + " leagues");
		
		int tableEntriesAdded = 0;
		for (LeagueObject league : leagueList.getList()) {
			TableEntryListObject existingTableEntries = tableEntryService.getTableEntries(league.getId());
			if(!existingTableEntries.isEmpty()){
				continue;
			}
			TableEntryListObject tableEntries = UrlParser.getTableEntryListObject(league, true);
			LOG.debug("Found " + tableEntries.size() + " tableEntries for league " + league.getId());
			for (TableEntryObject tableEntry : tableEntries) {
				tableEntry.setTeamId(0 - tableEntry.getPos());
				tableEntry.setId(-1);
				if (addOrUpdateTableEntry(tableEntry)) {
					tableEntriesAdded++;
				} else {
					LOG.error("Failed to tableentry match with leagueId " + tableEntry.getLeagueId());
				}
			}
		}
		LOG.debug("Updated or added " + tableEntriesAdded + " tableEntries");
	}
	
	
	private void checkIfOldMatchesIsEmpty() {
		LOG.debug("In checkIfOldMatchesIsEmpty");
		LeagueListObject leagueList = leagueService.getLeagues();
		
		LOG.debug("Found " + leagueList.size() + " leagues");
		
		int matchesAdded = 0;
		for (LeagueObject league : leagueList.getList()) {
			TeamObject team = teamService.getTeam(league.getTeam());
			MatchListObject existingMatches = matchService.getMatchesByLeague(league.getId());
			if(!existingMatches.isEmpty()){
				continue;
			}
			MatchListObject matches = UrlParser.getMatchListObject(team, league, true);
			LOG.debug("Found " + matches.size() + " matches for league " + league.getId());
			for (MatchObject match : matches) {
				match.setId(-1);
				if (addOrUpdateMatch(match)) {
					matchesAdded++;
				} else {
					LOG.error("Failed to tableentry match with leagueId " + match.getLeague());
				}
			}
		}
		LOG.debug("Updated or added " + matchesAdded + " tableEntries");
		
	}
	
}
