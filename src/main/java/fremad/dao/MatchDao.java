package fremad.dao;

import java.util.List;
import fremad.domain.MatchObject;

public interface MatchDao {
	public int deleteMatch(int matchId);
	public int deleteMatches(int leagueId);
	public List<MatchObject> getMatches(int leagueId);
	public void saveMatch(MatchObject match);
	public MatchObject getMatch(int matchId);
}
