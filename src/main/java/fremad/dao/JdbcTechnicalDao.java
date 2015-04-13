package fremad.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.BugObject;
import fremad.domain.list.BugListObject;

@Repository
public class JdbcTechnicalDao extends JdbcConnection implements TechnicalDao {
	private static final Logger LOG = LoggerFactory
			.getLogger(JdbcTechnicalDao.class);

	@Override
	public BugListObject getBugs(){
		LOG.debug("In getBugs()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_BUG;
		BugListObject bugs = new BugListObject();
		
		bugs.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(BugObject.class)));
		return bugs;
	}
	
	@Override
	public BugObject addBug(BugObject bugObject){
		LOG.debug("In addBug(bugObject)");

		SimpleJdbcInsert insertBug = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_BUG)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(bugObject);
		Number newId = insertBug.executeAndReturnKey(parameters);
		
		if(newId != null){
			bugObject.setId(newId.intValue());
			return bugObject;
		}
		
		return null;
		
	}
	
	@Override
	public BugObject updateBug(BugObject bugObject){
		LOG.debug("In updateBug(bugObject)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_BUG + " set "
				+ "user_id = :userId, "
				+ "title = :title, "
				+ "priority = :priority, "
				+ "os = :os, "
				+ "browser = :browser, "
				+ "problem = :problem, "
				+ "date = :date, "
				+ "fixed = :fixed "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(bugObject);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return bugObject;
	}
	
	@Override
	public BugObject deleteBug(BugObject bugObject){
		LOG.debug("In deleteBug(bugObject)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_BUG + " where id = :bugId";
		SqlParameterSource parameters = new MapSqlParameterSource("bugId", bugObject.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return bugObject;
	}
	
}
