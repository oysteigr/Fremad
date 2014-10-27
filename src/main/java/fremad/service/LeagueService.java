package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcLeagueDao;
import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;

@Service
@Scope("singleton")
public class LeagueService {
	
	@Autowired
	JdbcLeagueDao leagueDao;
	
	public LeagueObject addLeague(LeagueObject leagueObject){
		return leagueDao.addLeague(leagueObject);
	}
	
	public LeagueListObject getLeagues(){
		return leagueDao.getLeagues();
	}

}
