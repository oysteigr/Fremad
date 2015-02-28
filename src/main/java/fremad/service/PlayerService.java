package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcPlayerDao;
import fremad.domain.KeyValuePairObject;
import fremad.domain.PlayerNoteObject;
import fremad.domain.PlayerObject;
import fremad.domain.list.KeyValuePairListObject;
import fremad.domain.list.PlayerListObject;
import fremad.domain.list.PlayerNoteListObject;


@Service
@Scope("singleton")
public class PlayerService {
	
	@Autowired
	JdbcPlayerDao playerDao;
	
	public PlayerListObject getPlayers(){
		return playerDao.getPlayers();
	}
	public PlayerListObject getPlayersByTeam(int teamId){
		return playerDao.getPlayersByTeam(teamId);
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
	
	public PlayerNoteListObject getPlayerNotes(){
		return playerDao.getPlayerNotes();
	}
	public PlayerNoteObject addPlayerNote(PlayerNoteObject playerNoteObject){
		return playerDao.addPlayerNote(playerNoteObject);
	}
	public PlayerNoteObject deletePlayerNote(PlayerNoteObject playerNoteObject){
		return playerDao.deletePlayerNote(playerNoteObject);
	}
	
	public KeyValuePairListObject<Integer, Integer> getPlayerUserRelations() {
		return playerDao.getPlayerUserRelations();
	}
	public boolean addPlayerUserRelation(KeyValuePairObject<Integer, Integer> valuePair){
		return playerDao.addPlayerUserRelation(valuePair);
	}
	public boolean deletePlayerUserRelation(KeyValuePairObject<Integer, Integer> valuePair) {
		return playerDao.deletePlayerUserRelation(valuePair);
	}


}
