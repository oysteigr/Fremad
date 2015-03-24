package fremad.dao;

import fremad.domain.LeagueObject;
import fremad.domain.list.LeagueListObject;

public interface LeagueDao {
	LeagueListObject getLeagues();
	LeagueListObject getLeagues(int teamId);
	LeagueListObject getLeaguesByYear(int year);
	LeagueObject addLeague(LeagueObject league);
	LeagueObject updateLeague(LeagueObject league);
	LeagueObject deleteLeague(LeagueObject league);
}
