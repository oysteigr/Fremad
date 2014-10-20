package fremad.dao;

import java.util.List;
import fremad.domain.MatchObject;

public interface MatchDao {
	public boolean delete(int id);
	public List<MatchObject> listMatches();
	public void saveMatch(MatchObject match);
	public MatchObject getMatch(int id);
}
