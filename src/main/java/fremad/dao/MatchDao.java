package fremad.dao;

import fremad.domain.MatchListObject;
import fremad.domain.MatchObject;

public interface MatchDao {
	public int deleteMatch(int matchId);
	public int deleteMatches(int leagueId);
	public MatchListObject getMatches(int leagueId);
	public void saveMatch(MatchObject match);
	public MatchObject getMatch(int matchId);
}
