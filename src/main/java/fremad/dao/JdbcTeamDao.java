package fremad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import fremad.domain.TeamListObject;
import fremad.domain.TeamObject;
import fremad.dao.SqlTablesConstants;

public class JdbcTeamDao extends JdbcConnection implements TeamDao {
	
	public JdbcTeamDao() {
		super();
	}
	
	@Override
	public void addTeam(TeamObject team) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " "
				+ "(name, online_id) "
				+ "VALUES (?, ?)";
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setString(1, team.getName());
			prpstm.setInt(2, team.getOnlineId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public TeamListObject getTeams() {
		TeamListObject teams = new TeamListObject();
		
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH);
		try {
			while (res.next()) {
				teams.add(new TeamObject(-1, res.getString("name"), res.getInt("online_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teams;
	}
}
