package fremad.processor;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.KeyValuePairObject;
import fremad.domain.PlayerNoteObject;
import fremad.domain.PlayerObject;
import fremad.domain.list.KeyValuePairListObject;
import fremad.domain.list.PlayerListObject;
import fremad.domain.list.PlayerNoteListObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.exception.TechnicalErrorException;
import fremad.security.SessionSecurityContext;
import fremad.service.PlayerService;
import fremad.service.UserService;
import fremad.utils.StringCompare;


@Component
@Scope("request")
public class PlayerProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(PlayerProcessor.class);
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	SessionSecurityContext securityContext;
	
	@Autowired
	UserService userService;
	
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
					return comparePlayersByPos(obj1,obj2);
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


	public KeyValuePairListObject<Integer, Integer> getPlayerUserRelations() {
		
		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		return playerService.getPlayerUserRelations();
	}
	
	public void addPlayerUserRelation(KeyValuePairObject<Integer, Integer> valuePair) {
		
		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		if(!playerService.addPlayerUserRelation(valuePair)){
			throw new TechnicalErrorException(null, 0, "Could create connection");
		}
		
		LOG.debug("Got valuepair: " + valuePair.getKey().toString() + " ," + valuePair.getValue().toString());
		
	}

	public void deletePlayerUserRelation(KeyValuePairObject<Integer, Integer> valuePair) {
			
		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		if(!playerService.deletePlayerUserRelation(valuePair)){
			throw new TechnicalErrorException(null, 0, "Could delete connection");
		}
		
		LOG.debug("Delete valuepair: " + valuePair.getKey().toString() + " ," + valuePair.getValue().toString());
		
	}
	
	public KeyValuePairListObject<Integer, Integer> getSuggestedPlayerUserRelations(){
		LOG.debug("In getSuggestedPlayerUserRelations");
		
		securityContext.checkUserPremission(UserRoleEnum.ADMIN);
		
		UserMetaListObject userMetaList = getPlayerUserMeta();
		PlayerListObject playerList = playerService.getPlayers();
		KeyValuePairListObject<Integer, Integer> playerUserConnections = playerService.getPlayerUserRelations();
		
		LOG.debug("Dud 1");
		
		for (Iterator<PlayerObject> iter = playerList.iterator(); iter.hasNext(); ) {
			PlayerObject player = iter.next();
			for(KeyValuePairObject<Integer,Integer> playerUserConnection : playerUserConnections)
			if (playerUserConnection.getKey().intValue() == player.getId()) {
				iter.remove();
			}
		}
		LOG.debug("Dud 2");		
		for (Iterator<UserMetaObject> iter = userMetaList.iterator(); iter.hasNext(); ) {
			UserMetaObject user = iter.next();
			for(KeyValuePairObject<Integer,Integer> playerUserConnection : playerUserConnections)
			if (playerUserConnection.getValue().intValue() == user.getUserId()) {
				iter.remove();
			}
		}
		LOG.debug("Dud 3");		
		KeyValuePairListObject<Integer,Integer> suggestedConnections = 
				new KeyValuePairListObject<Integer,Integer>();
		for(UserMetaObject userMeta : userMetaList){
			for(PlayerObject player : playerList){
				LOG.debug("Player(" + player.getFirstName() + " " + player.getLastName() 
						+ " == User(" + userMeta.getFirstName() + " " + userMeta.getLastName() + ") compare value: " 
						+ StringCompare.score(player.getFirstName() + " " + player.getLastName(),userMeta.getFirstName() + " " + userMeta.getLastName()));
				if(StringCompare.score(
						userMeta.getFirstName() + "" + userMeta.getLastName(),
						player.getFirstName() + "" + player.getLastName()) > 0.5){
					suggestedConnections.add(new KeyValuePairObject<Integer,Integer>(player.getId(), userMeta.getUserId()));
				}
			}
		}
		
		return suggestedConnections;
	}
	
	private UserMetaListObject getPlayerUserMeta(){
		UserMetaListObject userMetaList = userService.getUsersMeta();
		UserMetaListObject userMetaListFiltered = new UserMetaListObject();
		
		UserObject temp;
		
		for(UserMetaObject userMetaObject : userMetaList){
			temp = userService.getUser(userMetaObject.getUserId());
			if(temp.getRole() > 1 && temp.isValidated()){
				userMetaListFiltered.add(userMetaObject);
			}
		}
		return userMetaListFiltered;
	}
	
	private int comparePlayersByPos(PlayerObject obj1, PlayerObject obj2){
		return getPosValue(obj1) - getPosValue(obj2);
	}
	
	private int getPosValue(PlayerObject obj){
		switch(obj.getPosition()){
		case "goalkeeper":
			return 1;
		case "defender":
			return 2;
		case "midfielder":
			return 3;
		case "attacker":
			return 4;
		}
		return 0;
	}

}
