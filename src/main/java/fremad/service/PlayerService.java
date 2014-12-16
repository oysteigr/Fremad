package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcPlayerDao;
import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;


@Service
@Scope("singleton")
public class PlayerService {
	
	@Autowired
	JdbcPlayerDao jdbcPlayerDao;
	
	public PlayerListObject getPlayers(){
		return jdbcPlayerDao.getPlayers();
	}
	public PlayerListObject getPlayersByTeam(int teamId){
		return jdbcPlayerDao.getPlayersByTeam(teamId);
	}
	public PlayerObject addPlayer(PlayerObject playerObject){
		return jdbcPlayerDao.addPlayer(playerObject);
	}
	
	public PlayerObject updatePlayer(PlayerObject playerObject){
		return jdbcPlayerDao.updatePlayer(playerObject);
	}
	
	public PlayerObject deletePlayer(PlayerObject playerObject){
		return jdbcPlayerDao.deletePlayer(playerObject);
	}

}
