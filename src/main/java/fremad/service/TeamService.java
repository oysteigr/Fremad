package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcTeamDao;
import fremad.domain.TeamListObject;
import fremad.domain.TeamObject;

@Service
@Scope("singleton")
public class TeamService {
	
	@Autowired
	JdbcTeamDao teamDao;
	
	public TeamListObject getTeams(){
		return teamDao.getTeams();
	}
	
	public TeamObject getTeam(int teamId) {
		return teamDao.getTeam(teamId);
	}

	public TeamObject addTeam(TeamObject teamObject){
		return teamDao.addTeam(teamObject);
	}

	public TeamObject updateTeam(TeamObject teamObject){
		return teamDao.updateTeam(teamObject);
	}

	public TeamObject deleteTeam(TeamObject teamObject){
		return teamDao.deleteTeam(teamObject);
	}

}	