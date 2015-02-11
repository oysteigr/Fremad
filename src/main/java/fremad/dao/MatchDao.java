package fremad.dao;

import fremad.domain.MatchObject;
import fremad.domain.list.MatchListObject;

public interface MatchDao {
	public int deleteMatch(int matchId);
	public int deleteMatches(int leagueId);
	public MatchListObject getMatches(int leagueId);
	public boolean addMatch(MatchObject match);
	public boolean updateMatch(MatchObject match);
	public MatchObject getMatch(int matchId);
}
