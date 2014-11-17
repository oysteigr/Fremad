package fremad.tools;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.domain.MatchListObject;
import fremad.domain.MatchObject;
import fremad.domain.TableEntryListObject;
import fremad.domain.TableEntryObject;
import fremad.domain.TeamObject;
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
		
		LeagueListObject leagueList = leagueService.getLeagues();
		
		LOG.debug("Found " + leagueList.size() + " leagues");
		
		int matchesAdded = 0;
		for (LeagueObject league : leagueList.getList()) {
			TeamObject team = teamService.getTeam(league.getTeam());
			MatchListObject matches = UrlParser.getMatchListObject(team);
			LOG.debug("Found " + matches.size() + " matches for team " + league.getTeam());
			for (MatchObject match : matches) {
				if (matchService.addMatch(match)) {
					matchesAdded++;
				} else {
					LOG.error("Failed to add match with id " + match.getId());
				}
			}
		}
		LOG.debug("Added " + matchesAdded + " matches");
		
	}
	
	@Scheduled(fixedRate = updateFrequency)
	public void updateTableTask() {
		LOG.debug("In updateTableTask");
		
		LeagueListObject leagueList = leagueService.getLeagues();
		
		LOG.debug("Found " + leagueList.size() + " leagues");
		
		int tableEntriesAdded = 0;
		for (LeagueObject league : leagueList.getList()) {
			TableEntryListObject tableEntryList = UrlParser.getTableEntryListObject(league);
			LOG.debug("Found " + tableEntryList.size() + " tableEntries for league " + league.getId());
			for (TableEntryObject tableEntry : tableEntryList) {
				if (tableEntryService.addTableEntry(tableEntry)) {
					tableEntriesAdded++;
				} else {
					LOG.error("Failed to add match with leagueId " + tableEntry.getLeagueId());
				}
			}
		}
		LOG.debug("Added " + tableEntriesAdded + " matches");
	}
}
