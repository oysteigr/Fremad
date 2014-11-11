package fremad.dao;

import java.util.List;
import fremad.domain.MatchObject;

public interface MatchDao {
	public boolean delete(int id);
	public List<MatchObject> listMatches(int leagueId);
	public void saveMatch(MatchObject match);
	public MatchObject getMatch(int matchId);
}
