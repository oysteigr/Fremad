package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcLeagueDao;
import fremad.domain.LeagueObject;
import fremad.domain.list.LeagueListObject;

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
	public LeagueListObject getLeaguesByYear(int year){
		return leagueDao.getLeaguesByYear(year);
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
