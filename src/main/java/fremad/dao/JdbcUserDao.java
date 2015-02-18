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

import fremad.domain.list.UserLoginLogListObject;
import fremad.domain.user.UserListObject;
import fremad.domain.user.UserLoginLogObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;
import fremad.domain.user.UserRoleRequestObject;

@Repository
public class JdbcUserDao extends JdbcConnection implements UserDao{
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcUserDao.class);
	
	//----------------------USER METHODS----------------------
	

	@Override
	public UserListObject getUsers() {
		LOG.debug("In getUsers()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER;
		UserListObject users = new UserListObject();
		
		users.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new UserBeanPropertyRowMapper<>(UserObject.class)));
		return users;
	}

	
	@Override
	public UserObject getUser(String userName) {
		LOG.debug("In getUser(userName)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER + " where user_name = :userName";
		SqlParameterSource parameters = new MapSqlParameterSource("userName", userName);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new UserBeanPropertyRowMapper<>(UserObject.class));
	}
	
	@Override
	public UserObject getUser(int userId) {
		LOG.debug("In getUser(userId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER + " where id = :id";
		SqlParameterSource parameters = new MapSqlParameterSource("id", userId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new UserBeanPropertyRowMapper<>(UserObject.class));
	}
	

	@Override
	public int addUser(UserObject userObject){
		LOG.debug("In addUser(userObject)");

		SimpleJdbcInsert insertUser = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_USER)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(userObject);
		Number newId = insertUser.executeAndReturnKey(parameters);
		
		if(newId != null){
			userObject.setId(newId.intValue());
			return newId.intValue();
		}
		
		return -1;
	}

	@Override
	public UserObject updateUser(UserObject userObject) {
		LOG.debug("In updateUser(userObject)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_USER + " set "
				+ "user_name = :userName, "
				+ "password = :password, "
				+ "salt = :salt, "
				+ "role = :role, "
				+ "validated = :validated "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(userObject);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return userObject;
	}
	
	@Override
	public UserObject updateUserRole(UserObject userObject) {
		LOG.debug("In updateUserRole(userObject)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_USER + " set "
				+ "role = :role "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(userObject);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return userObject;
	}

	@Override
	public UserObject deleteUser(UserObject userObject) {
		LOG.debug("In deleteUser(userObject)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_USER + " where id = :id";
		SqlParameterSource parameters = new MapSqlParameterSource("id", userObject.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return userObject;
	}
	
	@Override
	public void validateUser(int id) {
		LOG.debug("In validateUser(userName)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_USER + " set "
				+ "validated = :validated "
				+ "where id = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource("validated", true)
			.addValue("id", id);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return;
	}
	
	//----------------------USERMETA METHODS----------------------
	

	@Override
	public UserMetaListObject getUsersMeta(){
		LOG.debug("In getUsersMeta()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER_META;
		UserMetaListObject userMeta = new UserMetaListObject();
		
		userMeta.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new UserBeanPropertyRowMapper<>(UserMetaObject.class)));
		return userMeta;
	}
	
	@Override
	public UserMetaObject getUserMeta(int userId){
		LOG.debug("In getUserMeta(userId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " where user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new UserBeanPropertyRowMapper<>(UserMetaObject.class));
	}
	
	@Override
	public boolean addUserMeta(UserMetaObject usermetaObject){
		LOG.debug("In addUserMeta(usermetaObject)");

		SimpleJdbcInsert insertUser = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_USER_META);
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(usermetaObject);
		return insertUser.execute(parameters) > 0;
	}
	
	@Override
	public UserMetaObject updateUserMeta(UserMetaObject userMetaObject) {
		LOG.debug("In updateUserMeta(userMetaObject)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " set "
				+ "first_name = :firstName, "
				+ "last_name = :lastName, "
				+ "phone_number = :phoneNumber, "
				+ "birthday = :birthday, "
				+ "home_town = :homeTown, "
				+ "profession = :profession "
				+ "where user_id = :userId";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(userMetaObject);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return userMetaObject;
	}

	@Override
	public boolean deleteUserMeta(int userId) {
		LOG.debug("In deleteUserMeta(userId)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " where user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
		
		return namedParameterJdbcTemplate.update(query, parameters) > 0;
	}
	
	//----------------------USERLOG METHODS----------------------
	
	@Override
	public void loggUserLogin(int userId) {
		LOG.debug("In loggUserLogin(userId)");

		SimpleJdbcInsert insertLogg = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN);
		
		SqlParameterSource parameters = new MapSqlParameterSource("user_id", userId);
		
		insertLogg.execute(parameters);
		
		return;
	}

	@Override
	public UserLoginLogListObject getUserLogins(final int userId) {
		LOG.debug("In getUserLogins(userId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN  + " where user_id = ?";;
		UserLoginLogListObject userLogins = new UserLoginLogListObject();
		
		userLogins.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setInt(1, userId);
				}
			}, new BeanPropertyRowMapper<>(UserLoginLogObject.class)));
		
		return userLogins;
	}
	
	@Override
	public UserLoginLogListObject getUserLogins() {
		LOG.debug("In getUserLogins()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN;
		UserLoginLogListObject userLogins = new UserLoginLogListObject();
		
		userLogins.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new UserBeanPropertyRowMapper<>(UserLoginLogObject.class)));
		return userLogins;
	}
	

	@Override
	public boolean deleteUserLogsByUser(int userId) {
		LOG.debug("In deleteUserLogsByUser(userId)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN + " where user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
		
		return namedParameterJdbcTemplate.update(query, parameters) > 0;

	}
	
	//----------------------USER REQUEST METHODS----------------------
	
	@Override
	public boolean addUserRoleRequest(int userId, UserRoleEnum role) {
		String updateStatement = "insert into " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " set "
				+ "user_id = :userId, "
				+ "requested_role = :requestedRole";
		
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId)
			.addValue("requestedRole", role.getRoleValue());
		
		return this.namedParameterJdbcTemplate.update(updateStatement, parameters) > 0;
	}

	@Override
	public UserRoleRequestListObject getUserRoleRequests() {
		LOG.debug("In getUserRoleRequests()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST;
		UserRoleRequestListObject userRequests = new UserRoleRequestListObject();
		
		userRequests.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new UserBeanPropertyRowMapper<>(UserRoleRequestObject.class)));
		
		return userRequests;
	}

	@Override
	public boolean grantUserRoleRequest(int requestId) {
		LOG.debug("In grantUserRoleRequest(requestId)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " set "
				+ "accepted = :accepted "
				+ "where id = :id";
		
		SqlParameterSource parameters = new MapSqlParameterSource("accepted", true)
			.addValue("id", requestId);

		return this.namedParameterJdbcTemplate.update(updateStatement, parameters) > 0;
	}

	@Override
	public UserRoleRequestObject deleteUserRoleRequest(UserRoleRequestObject userRoleRequestObject) {
		LOG.debug("In deleteUserRoleRequest(userRoleRequestObject)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " where id = :id";
		SqlParameterSource parameters = new MapSqlParameterSource("id", userRoleRequestObject.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
	
		return userRoleRequestObject;
	}


	@Override
	public boolean deleteUserRoleRequestByUser(int userId) {
		LOG.debug("In deleteUserRoleRequestByUser(userId)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " where user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
		
		return namedParameterJdbcTemplate.update(query, parameters) > 0;
	}

	//----------------------USER VALIDATION METHODS----------------------

	@Override
	public boolean saveValidationCode(String code, int userId) {
		LOG.debug("In saveValidationCode(code, userId)");

		SimpleJdbcInsert insertLogg = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_USER_VALIDATION);
		
		SqlParameterSource parameters = new MapSqlParameterSource("user_id", userId)
			.addValue("code", code);
		
		return insertLogg.execute(parameters) > 0;
	}


	@Override
	public String getValidationCode(int userId) {
		String query = "select code from " + SqlTablesConstants.SQL_TABLE_NAME_USER_VALIDATION+ " where user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, String.class);
	}


	@Override
	public boolean deleteValidationCode(int userId) {
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_USER_VALIDATION + " where user_id = :userId";
		SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
		
		return namedParameterJdbcTemplate.update(query, parameters) > 0;
	}

}
