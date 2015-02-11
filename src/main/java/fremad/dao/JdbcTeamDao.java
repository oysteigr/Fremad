package fremad.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.TeamObject;
import fremad.domain.list.TeamListObject;

@Repository
public class JdbcTeamDao extends JdbcConnection implements TeamDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcTeamDao.class);
	public JdbcTeamDao() {
		super();
	}
	
	@Override
	public TeamListObject getTeams() {
		LOG.debug("In getTeams()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_TEAM;
		TeamListObject teams = new TeamListObject();
		
		teams.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(TeamObject.class)));
		return teams;
	}
	
	@Override
	public TeamObject getTeam(int teamId) {
		LOG.debug("In getTableEntry(tableEntryId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " where id = :teamId";
		SqlParameterSource parameters = new MapSqlParameterSource("teamId", teamId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new BeanPropertyRowMapper<>(TeamObject.class));
	}
	
	@Override
	public TeamObject addTeam(TeamObject teamObject) {
		LOG.debug("In addTeam(teamObject)");

		SimpleJdbcInsert insertTeam = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_TEAM)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(teamObject);
		Number newId = insertTeam.executeAndReturnKey(parameters);
		
		if(newId != null){
			teamObject.setId(newId.intValue());
			return teamObject;
		}
		
		return null;
	}

	@Override
	public TeamObject updateTeam(TeamObject teamObject) {
		LOG.debug("In updateTeam(teamObject)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " set "
				+ "name = :name, "
				+ "online_id = :onlineId "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(teamObject);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return teamObject;
	}
	
	@Override
	public TeamObject deleteTeam(TeamObject teamObject) {
		LOG.debug("In deleteTeam(teamObject)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_TEAM + " where id = :teamId";
		SqlParameterSource parameters = new MapSqlParameterSource("teamId", teamObject.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return teamObject;
	}
	
}
