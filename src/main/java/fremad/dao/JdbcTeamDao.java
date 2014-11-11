package fremad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.LeagueObject;
import fremad.domain.TeamListObject;
import fremad.domain.TeamObject;
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
		
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_TEAM);
		try {
			while (res.next()) {
				teams.add(new TeamObject(res.getInt("id"), res.getString("name"), res.getInt("online_id")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		return teams;
	}
	
	@Override
	public TeamObject getTeam(int teamId) {
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " WHERE id = " + teamId);
		try {
			if (res.next()) {
				return new TeamObject(res.getInt("id"), res.getString("name"), res.getInt("online_id"));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		return null;
	}
	
	@Override
	public TeamObject addTeam(TeamObject teamObject) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " "
				+ "(name, online_id) "
				+ "VALUES (?, ?)";
		int key = -1;
		try {
			prpstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setString(1, teamObject.getName());
			prpstm.setInt(2, teamObject.getOnlineId());
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
			ResultSet rs = prpstm.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		teamObject.setId(key);
		return teamObject;
	}

	@Override
	public TeamObject updateTeam(TeamObject teamObject) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " SET "
				+ " name = ?, "
				+ " online_id = ?, "
				+ " WHERE id = ?";
		
		LOG.debug("In addLeague with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setString(1, teamObject.getName());
			prpstm.setInt(2, teamObject.getOnlineId());
			prpstm.setInt(3, teamObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		return teamObject;
	}
	
	@Override
	public TeamObject deleteTeam(TeamObject teamObject) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " WHERE "
				+ " id = ?";
		
		LOG.debug("In deleteTeam with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, teamObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		return teamObject;
	}
	
}
