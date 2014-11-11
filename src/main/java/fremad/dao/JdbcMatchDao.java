package fremad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
		String sql = "DELETE FROM match WHERE id = ?";
		
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
		String sql = "DELETE FROM match WHERE league = ?";
		
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
			prpstm.setInt(0, matchId);
			ResultSet res = prpstm.executeQuery();
			if (res != null && res.next()) {
				return new MatchObject( res.getInt(0), 
										res.getInt(1), 
										res.getInt(2),
										res.getBoolean(3), 
										res.getInt(4), 
										res.getString(5),
										res.getInt(6),
										res.getInt(7),
										res.getDate(8),
										res.getString(9));
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
	}
	
	@Override
	public void saveMatch(MatchObject match) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_MATCH
						+ "(league, fremad_team, home_match, home_goals, opposing_team_name, opposing_team_id, opposing_team_goals, date, field "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, match.getLeague());
			prpstm.setInt(2, match.getFremad_team());
			prpstm.setBoolean(3, match.isHomeMatch());
			prpstm.setInt(4, match.getHomeGoals());
			prpstm.setString(5,  match.getOpposingTeamName());
			prpstm.setInt(6, match.getOpposingTeamId());
			prpstm.setInt(7, match.getOpposingTeamGoals());
			prpstm.setDate(8, new java.sql.Date(match.getDate().getTime()));
			prpstm.setString(9, match.getField());
			
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
	}

	@Override
	public MatchListObject getMatches(int leagueId) {
		
		MatchListObject matchList = new MatchListObject();
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + "WHERE league = " + leagueId);
		try {
			while (res.next()) {
				LOG.debug("Adding match");
				matchList.add(new MatchObject(res.getInt("id"),
												res.getInt("league"),
												res.getInt("fremad_team"), 
												res.getBoolean("home_match"), 
												res.getInt("home_goals"),
												res.getString("opposing_team_name"), 
												res.getInt("opposing_team_id"), 
												res.getInt("opposing_team_goals"),
												res.getDate("date"),
												res.getString("field")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		LOG.debug("Found " + matchList.size() + " matches");
		
		return matchList;
	}

}
