package fremad.dao;


import java.sql.SQLException;

import fremad.domain.LeagueObject;
import fremad.dao.SqlTablesConstants;

public class JdbcLeagueDao extends JdbcConnection implements LeagueDao {
	
	public JdbcLeagueDao() {
		super();
	}
	
	@Override
	public void addLeague(LeagueObject league) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " "
				+ "(year, team) "
				+ "VALUES (?, ?)";
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, league.getYear());
			prpstm.setInt(2, league.getTeam());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
