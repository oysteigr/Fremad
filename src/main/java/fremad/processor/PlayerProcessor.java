package fremad.processor;

import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.PlayerNoteObject;
import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;
import fremad.domain.list.PlayerNoteListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.PlayerService;


@Component
@Scope("request")
public class PlayerProcessor {
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	SessionSecurityContext securityContext;
	
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

		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return playerService.addPlayer(player);
	}
	
	public PlayerObject updatePlayer(PlayerObject player){

		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return playerService.updatePlayer(player);
	}
	
	public PlayerObject deletePlayer(PlayerObject player){

		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return playerService.deletePlayer(player);
	}
	
	public PlayerNoteListObject getPlayerNotes(){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return playerService.getPlayerNotes();
	}
	public PlayerNoteObject addPlayerNote(PlayerNoteObject playerNoteObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return playerService.addPlayerNote(playerNoteObject);
	}
	public PlayerNoteObject deletePlayerNote(PlayerNoteObject playerNoteObject){

		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return playerService.deletePlayerNote(playerNoteObject);
	}
}
