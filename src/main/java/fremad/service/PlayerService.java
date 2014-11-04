package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcPlayerDao;
import fremad.domain.PlayerListObject;
import fremad.domain.PlayerObject;


@Service
@Scope("singleton")
public class PlayerService {
	
	@Autowired
	JdbcPlayerDao playerDao;
	
	public PlayerListObject getPlayers(){
		return playerDao.getPlayers();
	}
	public PlayerObject addPlayer(PlayerObject playerObject){
		return playerDao.addPlayer(playerObject);
	}
	
	public PlayerObject updatePlayer(PlayerObject playerObject){
		return playerDao.updatePlayer(playerObject);
	}
	
	public PlayerObject deletePlayer(PlayerObject playerObject){
		return playerDao.deletePlayer(playerObject);
	}

}
