package fremad.dao;

import fremad.domain.TeamObject;
import fremad.domain.TeamListObject;

public interface TeamDao {
	public void addTeam(TeamObject team);
	public TeamListObject getTeams();
}
