package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcLeagueDao;
import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;

@Service
public class LeagueService {
	
	@Autowired
	JdbcLeagueDao leagueDao;
	
	public LeagueListObject getLeagues(){
		return leagueDao.getLeagues();
	}
	public LeagueListObject getLeagues(int teamId){
		return leagueDao.getLeagues(teamId);
	}
	public LeagueObject addLeague(LeagueObject leagueObject){
		return leagueDao.addLeague(leagueObject);
	}

	public LeagueObject updateLeague(LeagueObject leagueObject){
		return leagueDao.updateLeague(leagueObject);
	}

	public LeagueObject deleteLeague(LeagueObject leagueObject){
		return leagueDao.deleteLeague(leagueObject);
	}

}
