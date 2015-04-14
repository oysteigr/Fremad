package fremad.dao;

import fremad.domain.TeamObject;
import fremad.domain.list.TeamListObject;

public interface TeamDao {
	TeamListObject getTeams();
	TeamObject getTeam(int teamId);
	TeamObject addTeam(TeamObject teamObject);
	TeamObject updateTeam(TeamObject teamObject);
	TeamObject deleteTeam(TeamObject teamObject);
}
