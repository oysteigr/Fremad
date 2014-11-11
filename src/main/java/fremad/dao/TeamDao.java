package fremad.dao;

import fremad.domain.TeamObject;
import fremad.domain.TeamListObject;

public interface TeamDao {
	public TeamListObject getTeams();
	public TeamObject getTeam(int teamId);
	public TeamObject addTeam(TeamObject teamObject);
	public TeamObject updateTeam(TeamObject teamObject);
	public TeamObject deleteTeam(TeamObject teamObject);
}
