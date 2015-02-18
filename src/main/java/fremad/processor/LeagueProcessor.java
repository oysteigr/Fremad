package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.LeagueObject;
import fremad.domain.list.LeagueListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.LeagueService;

@Component
@Scope("request")
public class LeagueProcessor {
	
	@Autowired
	LeagueService leagueService;
	
	@Autowired
	SessionSecurityContext securityContext;
	
	public LeagueListObject getLeagues(){
		return leagueService.getLeagues();
	}
	public LeagueListObject getLeagues(int teamId){
		return leagueService.getLeagues(teamId);
	}
	public LeagueObject addLeague(LeagueObject leagueObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return leagueService.addLeague(leagueObject);
	}
	
	public LeagueObject updateLeague(LeagueObject leagueObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return leagueService.updateLeague(leagueObject);
	}
	
	public LeagueObject deleteLeague(LeagueObject leagueObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return leagueService.deleteLeague(leagueObject);
	}
}
