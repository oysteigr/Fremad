package fremad.dao;


import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;
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
		
		connect();
		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER);
		try {
			while (res != null && res.next()) {
				playerListObject.add(new PlayerObject(
					res.getInt("id"), 
					res.getString("first_name"),
					res.getString("last_name"),
					res.getInt("number"),
					res.getInt("member_since"),
					res.getString("position"),
					res.getString("preferred_foot"),
					res.getInt("team")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			close();
		}

		return playerListObject;
	}

	@Override
	public PlayerListObject getPlayersByTeam(int teamId) {
		
		PlayerListObject playerListObject = new PlayerListObject();
		
		connect();
		
		
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER + " WHERE team = ?";
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, teamId);
			res = prpstm.executeQuery();
			while (res != null && res.next()) {
				playerListObject.add(new PlayerObject(
					res.getInt("id"), 
					res.getString("first_name"),
					res.getString("last_name"),
					res.getInt("number"),
					res.getInt("member_since"),
					res.getString("position"),
					res.getString("preferred_foot"),
					res.getInt("team")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			close();
		}
		
		return playerListObject;
	}
	
	@Override
	public PlayerObject addPlayer(PlayerObject playerObject) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER 
				+ "(first_name, last_name, number, member_since, position, preferred_foot, team) "
				+ "VALUES(?,?,?,?,?,?,?)";
		int key = -1;
		
		connect();
		

		try {
			prpstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setString(1, playerObject.getFirstName());
			prpstm.setString(2, playerObject.getLastName());
			prpstm.setInt(3, playerObject.getNumber());
			prpstm.setInt(4, playerObject.getMemberSince());
			prpstm.setString(5, playerObject.getPossition());
			prpstm.setString(6, playerObject.getPreferredFoot());
			prpstm.setInt(7, playerObject.getTeam());
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
			res = prpstm.getGeneratedKeys();
			if (res != null && res.next()) {
				key = res.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			close();
		}
		playerObject.setId(key);
		
		return playerObject;
	}

	@Override
	public PlayerObject updatePlayer(PlayerObject playerObject) {
		LOG.debug("in updatePlayer");
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER 
				+ " SET first_name=?, last_name=?, number=?, member_since=?, position=?, preferred_foot=?, team=? WHERE id=?";
		connect();
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setString(1, playerObject.getFirstName());
			prpstm.setString(2, playerObject.getLastName());
			prpstm.setInt(3, playerObject.getNumber());
			prpstm.setInt(4, playerObject.getMemberSince());
			prpstm.setString(5, playerObject.getPossition());
			prpstm.setString(6, playerObject.getPreferredFoot());
			prpstm.setInt(7, playerObject.getTeam());
			prpstm.setInt(8, playerObject.getId());
			
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			close();
		}
		return playerObject;
	}

	@Override
	public PlayerObject deletePlayer(PlayerObject playerObject) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_PLAYER
				+ " WHERE id = ?";
		LOG.debug("In deletePlayer with sql: " + sql + " and id=" + playerObject.getId());
		
		connect();
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, playerObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			close();
		}
		
		return playerObject;
	}
	
}
