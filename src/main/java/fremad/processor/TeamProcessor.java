package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.TeamObject;
import fremad.domain.list.TeamListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.TeamService;

@Component
@Scope("request")
public class TeamProcessor {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private SessionSecurityContext securityContext;
	
	public TeamListObject getTeams(){
		return teamService.getTeams();
	}
	
	public TeamObject getTeam(int teamId) {
		return teamService.getTeam(teamId);
	}

	public TeamObject addTeam(TeamObject teamObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return teamService.addTeam(teamObject);
	}

	public TeamObject updateTeam(TeamObject teamObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return teamService.updateTeam(teamObject);
	}

	public TeamObject deleteTeam(TeamObject teamObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return teamService.deleteTeam(teamObject);
	}

}
