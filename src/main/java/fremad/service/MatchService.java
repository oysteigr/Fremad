package fremad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcMatchDao;
import fremad.domain.MatchObject;

@Service
@Scope("singleton")
public class MatchService {
	
	@Autowired
	JdbcMatchDao jdbcMatchDao;
	
	public MatchObject getMatch(int matchId){
		return jdbcMatchDao.getMatch(matchId);
		
	}
	public List<MatchObject> getMatches(int leagueId){
		return jdbcMatchDao.getMatches(leagueId);
	}
}
