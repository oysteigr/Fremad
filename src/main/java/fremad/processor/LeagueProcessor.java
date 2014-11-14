package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.service.LeagueService;

@Component
@Scope("request")
public class LeagueProcessor {
	
	@Autowired
	LeagueService leagueService;
	
	public LeagueListObject getLeagues(){
		return leagueService.getLeagues();
	}
	public LeagueListObject getLeagues(int teamId){
		return leagueService.getLeagues(teamId);
	}
	public LeagueObject addLeague(LeagueObject leagueObject){
		return leagueService.addLeague(leagueObject);
	}
	
	public LeagueObject updateLeague(LeagueObject leagueObject){
		return leagueService.updateLeague(leagueObject);
	}
	
	public LeagueObject deleteLeague(LeagueObject leagueObject){
		return leagueService.deleteLeague(leagueObject);
	}
}
