package fremad.dao;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.LeagueObject;
import fremad.domain.list.LeagueListObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcLeagueDao extends JdbcConnection implements LeagueDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcLeagueDao.class);
	
	public JdbcLeagueDao() {
		super();
	}
	
	@Override
	public LeagueListObject getLeagues() {
		LeagueListObject leagues = new LeagueListObject();
		
		connect();
		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE);
		try {
			while (res.next()) {
				leagues.add(new LeagueObject(res.getInt("id"), res.getString("name"), res.getInt("year"), res.getInt("team")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		
		return leagues;
	}
	
	@Override
	public LeagueListObject getLeagues(int teamId) {
		LeagueListObject leagues = new LeagueListObject();
		
		connect();
		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " WHERE team = " + teamId);
		try {
			while (res.next()) {
				leagues.add(new LeagueObject(res.getInt("id"), res.getString("name"), res.getInt("year"), res.getInt("team")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		
		return leagues;
	}
	
	@Override
	public LeagueObject addLeague(LeagueObject league) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " "
				+ "(id, name, year, team) "
				+ "VALUES (?, ?, ?, ?)";
		LOG.debug("In addLeague with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, league.getId());
			prpstm.setString(2, league.getName());
			prpstm.setInt(3, league.getYear());
			prpstm.setInt(4, league.getTeam());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		return league;
	}
	
	@Override
	public LeagueObject updateLeague(LeagueObject league) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " SET "
				+ " name = ?, "
				+ " year = ?, "
				+ " team = ? "
				+ " WHERE id = ?";
		
		LOG.debug("In addLeague with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setString(1, league.getName());
			prpstm.setInt(2, league.getYear());
			prpstm.setInt(3, league.getTeam());
			prpstm.setInt(4, league.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return league;
	}
	
	@Override
	public LeagueObject deleteLeague(LeagueObject league) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " WHERE "
				+ " id = ?";
		
		LOG.debug("In addLeague with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, league.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return league;
	}
	
}
