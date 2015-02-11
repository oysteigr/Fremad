package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.TeamObject;
import fremad.domain.list.TeamListObject;
import fremad.service.TeamService;

@Component
@Scope("request")
public class TeamProcessor {
	
	@Autowired
	TeamService teamService;
	
	public TeamListObject getTeams(){
		return teamService.getTeams();
	}
	
	public TeamObject getTeam(int teamId) {
		return teamService.getTeam(teamId);
	}

	public TeamObject addTeam(TeamObject teamObject){
		return teamService.addTeam(teamObject);
	}

	public TeamObject updateTeam(TeamObject teamObject){
		return teamService.updateTeam(teamObject);
	}

	public TeamObject deleteTeam(TeamObject teamObject){
		return teamService.deleteTeam(teamObject);
	}

}
