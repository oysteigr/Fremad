package fremad.dao;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fremad.domain.LeagueObject;
import fremad.dao.SqlTablesConstants;

public class JdbcLeagueDao extends JdbcConnection implements LeagueDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcLeagueDao.class);
	
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
			LOG.error(e.toString());
		}
	}
}
