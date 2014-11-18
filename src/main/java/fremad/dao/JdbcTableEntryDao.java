package fremad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.TableEntryListObject;
import fremad.domain.TableEntryObject;
import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcTableEntryDao extends JdbcConnection implements TableEntryDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcTableEntryDao.class);
	
	public JdbcTableEntryDao() {
		super();
	}
	
	@Override
	public int deleteTableEntry (int tableEntryId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " WHERE id = ?";
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(0, tableEntryId);
			return prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return -1;
		}
	}
	
	@Override
	public int deleteTableEntries(int leagueId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " WHERE league = ?";
		
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
	public TableEntryObject getTableEntry(int tableEntryId) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY	+ " WHERE id = ?";
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, tableEntryId);
			ResultSet res = prpstm.executeQuery();
			if (res != null && res.next()) {
				return new TableEntryObject( res.getInt(1), 
										res.getInt(2), 
										res.getInt(3),
										res.getString(4), 
										res.getInt(5), 
										res.getInt(6),
										res.getInt(7),
										res.getInt(8),
										res.getInt(9),
										res.getInt(10),
										res.getInt(11),
										res.getInt(12));
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
	}
	
	@Override
	public boolean addTableEntry(TableEntryObject tableEntry) {
		String sql = "INSERT IGNORE INTO " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " "
						+ "(league, pos, team_name, team_id, match_count, goals_scored, "
						+ "goals_conceded, points, games_won, games_tied, games_lost) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, tableEntry.getLeagueId());
			prpstm.setInt(2, tableEntry.getPos());
			prpstm.setString(3, tableEntry.getTeamName());
			prpstm.setInt(4, tableEntry.getTeamId());
			prpstm.setInt(5, tableEntry.getMatchCount());
			prpstm.setInt(6, tableEntry.getGoalsScored());
			prpstm.setInt(7, tableEntry.getGoalsConceded());
			prpstm.setInt(8, tableEntry.getPoints());
			prpstm.setInt(9, tableEntry.getGamesWon());
			prpstm.setInt(10, tableEntry.getGamesTied());
			prpstm.setInt(11, tableEntry.getGamesLost());
			prpstm.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		}
	}

	@Override
	public TableEntryListObject getTableEntries(int leagueId) {
		
		TableEntryListObject tableEntryList = new TableEntryListObject();
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " WHERE league = " + leagueId;
		LOG.debug(sql);
		ResultSet res = select(sql);
		try {
			while (res.next()) {
				LOG.debug("Adding TableEntryObject");
				tableEntryList.add(new TableEntryObject( res.getInt(1), 
												res.getInt(2), 
												res.getInt(3),
												res.getString(4), 
												res.getInt(5), 
												res.getInt(6),
												res.getInt(7),
												res.getInt(8),
												res.getInt(9),
												res.getInt(10),
												res.getInt(11),
												res.getInt(12)));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
		
		LOG.debug("Found " + tableEntryList.size() + " tableEntries");
		
		return tableEntryList;
	}
}
