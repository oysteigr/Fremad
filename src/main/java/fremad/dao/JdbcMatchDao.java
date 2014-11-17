package fremad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.MatchListObject;
import fremad.domain.MatchObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcMatchDao extends JdbcConnection implements MatchDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcMatchDao.class);
	
	public JdbcMatchDao() {
		super();
	}
	
	@Override
	public int deleteMatch(int matchId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " WHERE id = ?";
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(0, matchId);
			return prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return -1;
		}
	}
	
	@Override
	public int deleteMatches(int leagueId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " WHERE league = ?";
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(0, leagueId);
			return prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return -1;
		}
	}
	
	@Override
	public MatchObject getMatch(int matchId) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH	+ " WHERE id = ?";
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, matchId);
			ResultSet res = prpstm.executeQuery();
			if (res != null && res.next()) {
				return new MatchObject( res.getInt(1), 
										res.getInt(2), 
										res.getInt(3),
										res.getBoolean(4), 
										res.getInt(5), 
										res.getString(6),
										res.getInt(7),
										res.getInt(8),
										res.getTimestamp(9),
										res.getString(10));
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
	}
	
	@Override
	public boolean addMatch(MatchObject match) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " "
						+ "(league, team, home_match, fremad_goals, opposing_team_name, opposing_team_id, opposing_team_goals, date, field) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, match.getLeague());
			prpstm.setInt(2, match.getFremad_team());
			prpstm.setInt(3, match.isHomeMatch() ? 1 : 0);
			prpstm.setInt(4, match.getFremadGoals());
			prpstm.setString(5, match.getOpposingTeamName());
			prpstm.setInt(6, match.getOpposingTeamId());
			prpstm.setInt(7, match.getOpposingTeamGoals());
			prpstm.setTimestamp(8, match.getDate());
			prpstm.setString(9, match.getField());
			prpstm.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		}
	}

	@Override
	public MatchListObject getMatches(int leagueId) {
		
		MatchListObject matchList = new MatchListObject();
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " WHERE league = " + leagueId;
		LOG.debug(sql);
		ResultSet res = select(sql);
		try {
			while (res.next()) {
				LOG.debug("Adding match");
				matchList.add(new MatchObject(res.getInt("id"),
												res.getInt("league"),
												res.getInt("team"), 
												res.getBoolean("home_match"), 
												res.getInt("fremad_goals"),
												res.getString("opposing_team_name"), 
												res.getInt("opposing_team_id"), 
												res.getInt("opposing_team_goals"),
												res.getTimestamp("date"),
												res.getString("field")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		LOG.debug("Found " + matchList.size() + " matches");
		
		return matchList;
	}

}
