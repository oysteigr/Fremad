package fremad.dao;

import fremad.domain.PlayerNoteObject;
import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;
import fremad.domain.list.PlayerNoteListObject;

public interface PlayerDao {
	PlayerListObject getPlayers();
	PlayerListObject getPlayersByTeam(int teamId);
	PlayerObject addPlayer(PlayerObject player);
	PlayerObject updatePlayer(PlayerObject player);
	PlayerObject deletePlayer(PlayerObject player);
	
	PlayerNoteListObject getPlayerNotes();
	PlayerNoteObject addPlayerNote(PlayerNoteObject playerNoteObject);
	PlayerNoteObject deletePlayerNote(PlayerNoteObject playerNoteObject);

}
