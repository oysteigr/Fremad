package fremad.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcMatchDao;
import fremad.domain.MatchListObject;
import fremad.domain.MatchObject;

@Service
@Scope("singleton")
public class MatchService {
	
	@Autowired
	JdbcMatchDao jdbcMatchDao;
	
	
	public MatchObject getMatch(int matchId){
		return jdbcMatchDao.getMatch(matchId);
		
	}
	public MatchListObject getMatches(int leagueId){
		return jdbcMatchDao.getMatches(leagueId);
	}
	public boolean addMatch(MatchObject match) {
		return jdbcMatchDao.addMatch(match);
	}
	public int addMatches(MatchListObject matchList) {
		int matchesAdded = 0;
		for (MatchObject match : matchList) {
			if (jdbcMatchDao.addMatch(match)) {
				matchesAdded++;
			}
		}
		
		return matchesAdded;
	}
}
