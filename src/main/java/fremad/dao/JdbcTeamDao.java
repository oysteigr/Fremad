package fremad.dao;


import java.sql.SQLException;

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
}
