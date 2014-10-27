package fremad.dao;

import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;

public interface LeagueDao {
	public LeagueObject addLeague(LeagueObject league);
	public LeagueListObject getLeagues();
}
