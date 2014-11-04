package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.PlayerListObject;
import fremad.domain.PlayerObject;
import fremad.service.PlayerService;


@Component
@Scope("request")
public class PlayerProcessor {
	
	@Autowired
	PlayerService playerService;
	
	public PlayerListObject getPlayers(){
		return playerService.getPlayers();
	}
	public PlayerObject addPlayer(PlayerObject player){
		return playerService.addPlayer(player);
	}
	
	public PlayerObject updatePlayer(PlayerObject player){
		return playerService.updatePlayer(player);
	}
	
	public PlayerObject deletePlayer(PlayerObject player){
		return playerService.deletePlayer(player);
	}
}
