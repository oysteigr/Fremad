package fremad.dao;


import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.TeamObject;
import fremad.domain.list.TeamListObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcTeamDao extends JdbcConnection implements TeamDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcTeamDao.class);
	public JdbcTeamDao() {
		super();
	}
	
	@Override
	public TeamListObject getTeams() {
		TeamListObject teams = new TeamListObject();
		connect();		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_TEAM);
		

		
		try {
			while (res!= null && res.next()) {
				teams.add(new TeamObject(res.getInt("id"), res.getString("name"), res.getInt("online_id")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		
		return teams;
	}
	
	@Override
	public TeamObject getTeam(int teamId) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_TEAM	+ " WHERE id = ?";
		
		LOG.debug(sql);
		
		connect();

		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, teamId);
			res = prpstm.executeQuery();
			if (res != null && res.next()) {
				return new TeamObject( res.getInt(1), 
										res.getString(2), 
										res.getInt(3));
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
	}
	
	@Override
	public TeamObject addTeam(TeamObject teamObject) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " "
				+ "(name, online_id) "
				+ "VALUES (?, ?)";
		
		connect();
		
		int key = -1;
		try {
			prpstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setString(1, teamObject.getName());
			prpstm.setInt(2, teamObject.getOnlineId());
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
			res = prpstm.getGeneratedKeys();
			if (res != null && res.next()) {
				key = res.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		teamObject.setId(key);
		return teamObject;
	}

	@Override
	public TeamObject updateTeam(TeamObject teamObject) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " SET "
				+ " name = ?, "
				+ " online_id = ? "
				+ " WHERE id = ?";
		
		connect();
		
		
		LOG.debug("In updateTeam with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setString(1, teamObject.getName());
			prpstm.setInt(2, teamObject.getOnlineId());
			prpstm.setInt(3, teamObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return teamObject;
	}
	
	@Override
	public TeamObject deleteTeam(TeamObject teamObject) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " WHERE "
				+ " id = ?";
		
		connect();
		
		
		LOG.debug("In deleteTeam with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, teamObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return teamObject;
	}
	
}
