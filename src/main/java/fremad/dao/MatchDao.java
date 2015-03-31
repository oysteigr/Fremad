package fremad.dao;

import fremad.domain.MatchObject;
import fremad.domain.list.MatchListObject;

public interface MatchDao {
	int deleteMatch(int matchId);
	int deleteMatches(int leagueId);
	MatchListObject getMatches(int leagueId);
	MatchListObject getMatchesByLeague(int leagueId);
	MatchListObject getThisYearsMatches();
	boolean addMatch(MatchObject match);
	boolean updateMatch(MatchObject match);
	MatchObject getMatch(int matchId);
}
