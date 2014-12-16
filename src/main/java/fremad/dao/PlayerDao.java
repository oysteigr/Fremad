package fremad.dao;

import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;

public interface PlayerDao {
	public PlayerListObject getPlayers();
	public PlayerListObject getPlayersByTeam(int teamId);
	public PlayerObject addPlayer(PlayerObject player);
	public PlayerObject updatePlayer(PlayerObject player);
	public PlayerObject deletePlayer(PlayerObject player);

}
