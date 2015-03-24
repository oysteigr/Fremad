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

import fremad.domain.LeagueObject;
import fremad.domain.list.LeagueListObject;

@Repository
public class JdbcLeagueDao extends JdbcConnection implements LeagueDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcLeagueDao.class);
	
	public JdbcLeagueDao() {
		super();
	}
	
	@Override
	public LeagueListObject getLeagues() {
		LOG.debug("In getLeagues()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " ORDER BY year DESC;";
		LeagueListObject leagues = new LeagueListObject();
		
		leagues.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(LeagueObject.class)));
		return leagues;
	}
	
	@Override
	public LeagueListObject getLeagues(final int teamId) {
		LOG.debug("In getLeagues(teamId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " where team = ? ORDER BY year DESC;";
		LeagueListObject leagues = new LeagueListObject();
		
		leagues.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setInt(1, teamId);
				}
			}, new BeanPropertyRowMapper<>(LeagueObject.class)));
		
		return leagues;
	}
	
	@Override
	public LeagueListObject getLeaguesByYear(final int year) {
		LOG.debug("In getLeaguesByYear(year)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " where year = ?";
		LeagueListObject leagues = new LeagueListObject();
		
		leagues.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setInt(1, year);
				}
			}, new BeanPropertyRowMapper<>(LeagueObject.class)));
		
		return leagues;
	}
	
	@Override
	public LeagueObject addLeague(LeagueObject league) {
		LOG.debug("In addLeague(league)");
		
		SimpleJdbcInsert insertLeague = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_LEAGUE);
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(league);
		
		insertLeague.execute(parameters);
		
		return league;
	}
	
	@Override
	public LeagueObject updateLeague(LeagueObject league) {
		LOG.debug("In updateLeague(league)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " set "
				+ "name = :name, "
				+ "year = :year, "
				+ "team = :team "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(league);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return league;
	}
	
	@Override
	public LeagueObject deleteLeague(LeagueObject league) {
		LOG.debug("In deleteLeague(league)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_LEAGUE + " where id = :leagueId";
		SqlParameterSource parameters = new MapSqlParameterSource("leagueId", league.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return league;
	}


	
}
