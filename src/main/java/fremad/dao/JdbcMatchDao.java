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

import fremad.domain.MatchObject;
import fremad.domain.list.MatchListObject;

@Repository
public class JdbcMatchDao extends JdbcConnection implements MatchDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcMatchDao.class);
	
	public JdbcMatchDao() {
		super();
	}
	
	@Override
	public int deleteMatch(int matchId) {
		LOG.debug("In deleteMatch(matchId)");	
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " where id = :matchId";
		SqlParameterSource parameters = new MapSqlParameterSource("matchId", matchId);
		
		return namedParameterJdbcTemplate.update(query, parameters);
		
	}
	
	@Override
	public int deleteMatches(int leagueId) {
		LOG.debug("In deleteMatches(leagueId)");		
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " where league = :leagueId";
		SqlParameterSource parameters = new MapSqlParameterSource("leagueId", leagueId);
		
		return namedParameterJdbcTemplate.update(query, parameters);
	}
	
	@Override
	public MatchObject getMatch(int matchId) {
		LOG.debug("In getMatch(matchId)");		
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " where id = :matchId";
		SqlParameterSource parameters = new MapSqlParameterSource("matchId", matchId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new BeanPropertyRowMapper<>(MatchObject.class));
	}
	
	@Override
	public boolean addMatch(MatchObject match) {
		LOG.debug("In addMatch(match)");	

		SimpleJdbcInsert insertMatch = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_MATCH)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(match);
		Number newId = insertMatch.executeAndReturnKey(parameters);
		
		if(newId != null){
			match.setId(newId.intValue());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateMatch(MatchObject match) {
		LOG.debug("In addMatch(match)");		
		String updateStatement;
	
		LOG.debug("Where id and league: " + match.getLeague());
		updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_MATCH + " set "
				+ "league = :league, "
				+ "team = :fremadTeam, "
				+ "home_match = :homeMatch, "
				+ "fremad_goals = :fremadGoals, "
				+ "opposing_team_name = :opposingTeamName, "
				+ "opposing_team_id = :opposingTeamId, "
				+ "opposing_team_goals = :opposingTeamGoals, "
				+ "date = :date, "
				+ "field = :field where "
				+ "id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(match);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return true;

	}
	
	@Override
	public MatchListObject getMatches(final int teamId) {
		LOG.debug("In getMatches(teamId)");
		
		String query = "select * from" + SqlTablesConstants.SQL_TABLE_NAME_MATCH + "where team = ?";
		MatchListObject matchList = new MatchListObject();
		
		matchList.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setInt(1, teamId);
				}
			}, new BeanPropertyRowMapper<>(MatchObject.class)));
		
		LOG.debug("Found " + matchList.size() + " matches");
		
		return matchList;
	}

}
