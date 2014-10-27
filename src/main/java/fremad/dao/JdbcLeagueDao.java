package fremad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcLeagueDao extends JdbcConnection implements LeagueDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcLeagueDao.class);
	
	public JdbcLeagueDao() {
		super();
	}
	
	@Override
	public LeagueObject addLeague(LeagueObject league) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " "
				+ "(id, name, year, team) "
				+ "VALUES (?, ?, ?, ?)";
//		int key = -1;
		LOG.debug("In addLeague with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
//			prpstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setInt(1, league.getId());
			prpstm.setString(2, league.getName());
			prpstm.setInt(3, league.getYear());
			prpstm.setInt(4, league.getTeam());
			prpstm.executeUpdate();
//			ResultSet keys = prpstm.getGeneratedKeys();
//			keys.next();
//			key = keys.getInt(1);
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
//		league.setId(key);
		return league;
	}
	
	@Override
	public LeagueListObject getLeagues() {
		LeagueListObject leagues = new LeagueListObject();
		
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE);
		try {
			while (res.next()) {
				leagues.add(new LeagueObject(res.getInt("id"), res.getString("name"), res.getInt("year"), res.getInt("team")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		return leagues;
	}
}
