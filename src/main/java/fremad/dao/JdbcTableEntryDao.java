package fremad.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.TableEntryObject;
import fremad.domain.list.TableEntryListObject;

@Repository
public class JdbcTableEntryDao extends JdbcConnection implements TableEntryDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcTableEntryDao.class);
	
	public JdbcTableEntryDao() {
		super();
	}
	
	@Override
	public int deleteTableEntry (int tableEntryId) {
		LOG.debug("In deleteTableEntry(tableEntryId)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " where id = :tableEntryId";
		SqlParameterSource parameters = new MapSqlParameterSource("tableEntryId", tableEntryId);
		
		return namedParameterJdbcTemplate.update(query, parameters);
	}
	
	@Override
	public int deleteTableEntries(int leagueId) {
		LOG.debug("In deleteTableEntries(leagueId)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " where league_id = :leagueId";
		SqlParameterSource parameters = new MapSqlParameterSource("leagueId", leagueId);
		
		return namedParameterJdbcTemplate.update(query, parameters);
	}
	
	@Override
	public TableEntryObject getTableEntry(int tableEntryId) {
		LOG.debug("In getTableEntry(tableEntryId)");		
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " where id = :tableEntryId";
		SqlParameterSource parameters = new MapSqlParameterSource("tableEntryId", tableEntryId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new BeanPropertyRowMapper<>(TableEntryObject.class));
	}
	
	@Override
	public boolean addTableEntry(TableEntryObject tableEntry) {
		LOG.debug("In addTableEntry(tableEntry)");	

		SimpleJdbcInsert insertTableEntry = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(tableEntry);
		Number newId = insertTableEntry.executeAndReturnKey(parameters);
		
		if(newId != null){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateTableEntry(TableEntryObject tableEntry) {
		LOG.debug("In updateTableEntry(tableEntry)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " set "
				+ "pos = :pos, "
				+ "team_name = :teamName, "
				+ "match_count = :matchCount, "
				+ "goals_scored = :goalsScored, "
				+ "goals_conceded = :goalsConceded, "
				+ "points = :points, "
				+ "games_won = :gamesWon, "
				+ "games_tied = :gamesTied, "
				+ "games_lost = :gamesLost "
				+ "where id = :id";
		
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(tableEntry);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		return true;
	}

	@Override
	public TableEntryListObject getTableEntries(final int leagueId) {
		LOG.debug("In getTableEntries(leagueId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_TABLE_ENTRY + " where league_id = ?";
		TableEntryListObject tableEntryList = new TableEntryListObject();
		
		tableEntryList.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setInt(1, leagueId);
				}
			}, new BeanPropertyRowMapper<>(TableEntryObject.class)));
		
		return tableEntryList;
	}
}
