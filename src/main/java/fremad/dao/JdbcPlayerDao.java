package fremad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.PlayerListObject;
import fremad.domain.PlayerObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcPlayerDao extends JdbcConnection implements PlayerDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcPlayerDao.class);
	
	public JdbcPlayerDao() {
		super();
	}

	@Override
	public PlayerListObject getPlayers() {
		PlayerListObject playerListObject = new PlayerListObject();
		// TODO Auto-generated method stub
		return playerListObject;
	}

	@Override
	public PlayerObject addPlayer(PlayerObject player) {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public PlayerObject updatePlayer(PlayerObject player) {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public PlayerObject deletePlayer(PlayerObject player) {
		// TODO Auto-generated method stub
		return player;
	}
	
}
