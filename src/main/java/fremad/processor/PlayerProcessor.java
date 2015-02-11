package fremad.processor;

import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;
import fremad.service.PlayerService;


@Component
@Scope("request")
public class PlayerProcessor {
	
	@Autowired
	PlayerService playerService;
	
	public PlayerListObject getPlayers(){
		PlayerListObject response = playerService.getPlayers();
		if (response.size() > 0){
			Collections.sort(response.getList(), new Comparator<PlayerObject>(){
				@Override
				public int compare(final PlayerObject obj1, final PlayerObject obj2){
					return obj1.getLastName().compareTo(obj2.getLastName());
				}
			});
		}
		return response;
	}
	
	public PlayerListObject getPlayersByTeam(int teamId){
		PlayerListObject response = playerService.getPlayersByTeam(teamId);
		if (response.size() > 0){
			Collections.sort(response.getList(), new Comparator<PlayerObject>(){
				@Override
				public int compare(final PlayerObject obj1, final PlayerObject obj2){
					return obj1.getLastName().compareTo(obj2.getLastName());
				}
			});
		}
		return response;
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
