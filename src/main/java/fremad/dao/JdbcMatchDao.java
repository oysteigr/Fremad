package fremad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fremad.domain.MatchObject;
import fremad.dao.SqlTablesConstants;

public class JdbcMatchDao extends JdbcConnection implements MatchDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcMatchDao.class);
	
	public JdbcMatchDao() {
		super();
	}
	
	@Override
	public boolean delete(int id) {
		if (update("DELETE FROM match WHERE id = " + id) > 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public MatchObject getMatch(int id) {
		List<MatchObject> matchList = listMatches();
		if (matchList.size() > id) {
			return matchList.get(id);
		}
		return null;
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
			// prpstm.setBoolean(3, HOME_MATCH);
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
	public List<MatchObject> listMatches() {
		
		List<MatchObject> matchList = new ArrayList<MatchObject>();
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_MATCH);
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
