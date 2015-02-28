package fremad.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcMatchDao;
import fremad.domain.MatchObject;
import fremad.domain.list.MatchListObject;

@Service
@Scope("singleton")
public class MatchService {
	
	@Autowired
	JdbcMatchDao jdbcMatchDao;
	
	
	public MatchObject getMatch(int matchId){
		return jdbcMatchDao.getMatch(matchId);
		
	}
	public MatchListObject getMatches(int teamId){
		return jdbcMatchDao.getMatches(teamId);
	}
	public MatchListObject getThisYearsMatches(){
		return jdbcMatchDao.getThisYearsMatches();
	}
	public boolean addMatch(MatchObject match) {
		return jdbcMatchDao.addMatch(match);
	}
	public boolean updateMatch(MatchObject match) {
		return jdbcMatchDao.updateMatch(match);
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
