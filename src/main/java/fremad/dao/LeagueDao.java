package fremad.dao;

import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;

public interface LeagueDao {
	public LeagueListObject getLeagues();
	public LeagueListObject getLeagues(int teamId);
	public LeagueObject addLeague(LeagueObject league);
	public LeagueObject updateLeague(LeagueObject league);
	public LeagueObject deleteLeague(LeagueObject league);
}
