package fremad.dao;

import fremad.domain.PlayerListObject;
import fremad.domain.PlayerObject;

public interface PlayerDao {
	public PlayerListObject getPlayers();
	public PlayerListObject getPlayersByTeam(int teamId);
	public PlayerObject addPlayer(PlayerObject player);
	public PlayerObject updatePlayer(PlayerObject player);
	public PlayerObject deletePlayer(PlayerObject player);

}
