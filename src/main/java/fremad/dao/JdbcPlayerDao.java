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
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER 
				+ "(id, first_name, last_name, member_since, position, preferred_foot, team) "
				+ "VALUES("
				+ player.getId()
				+ player.getFirstName()
				+ player.getLastName()
				+ player.getMemberSince()
				+ player.getPossition()
				+ player.getPreferredFoot()
				+ player.getTeam();
		try {
			this.prpstm = this.conn.prepareStatement(sql);
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		
		return player;
	}

	@Override
	public PlayerObject updatePlayer(PlayerObject player) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER 
				+ "SET id=?, first_name=?, last_name=?, member_since=?, position=?, preferred_foot=?, team=?";
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, player.getId());
			prpstm.setString(2, player.getFirstName());
			prpstm.setString(3, player.getLastName());
			prpstm.setInt(4, player.getMemberSince());
			prpstm.setString(5, player.getPossition());
			prpstm.setString(6, player.getPreferredFoot());
			prpstm.setInt(7, player.getTeam());
			
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		return player;
	}

	@Override
	public PlayerObject deletePlayer(PlayerObject player) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER
				+ "WHERE id=?";
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(0, player.getId());
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		
		return player;
	}
	
}
