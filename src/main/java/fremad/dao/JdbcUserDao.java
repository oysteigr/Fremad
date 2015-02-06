package fremad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.user.UserListObject;
import fremad.domain.user.UserLoginLogListObject;
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
	
    private SimpleJdbcInsert insertEmployee;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	public UserListObject getUserss() {
        String query = "select * from employees";
        UserListObject users = new UserListObject();
        
        users.addAll(namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(UserObject.class)));
        return users;
    }
	
/*    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.insertEmployee = new SimpleJdbcInsert(dataSource).withTableName(SqlTablesConstants.SQL_TABLE_NAME_USER);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
  */  
	@Override
	public UserListObject getUsers(){
		LOG.debug("in getUsers");
		UserListObject users = new UserListObject();
		connect();		
		//res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER);
		ResultSet rs = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER);
		
		try {
			while (rs!= null && rs.next()) {
				users.add(new UserObject(
						rs.getInt("id"), 
						rs.getString("user_name"), 
						UserRoleEnum.valueOf(rs.getString("role")).getRoleValue(),
						rs.getTimestamp("created"), 
						rs.getBoolean("validated")));
			} 
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			try {
				if(rs != null && !rs.isClosed()){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeAll();
		}
		
		return users;

	}


	@Override
	public int addUser(UserObject userObject) throws SQLException {
		LOG.debug("in addUser");
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER + " "
				+ "(user_name, password, salt) "
				+ "VALUES (?, ?, ?)";
		int key = -1;
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setString(1, userObject.getUserName());
			prpstm.setString(2, userObject.getPassword());
			prpstm.setString(3, userObject.getSalt());
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
			res = prpstm.getGeneratedKeys();
			if (res != null && res.next()) {
				key = res.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			throw new SQLException("Sql error");
		} finally {
			closeAll();
		}
		return key;
	}
	


	@Override
	public UserObject getUser(String userName) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER	+ " WHERE user_name = ?";
		
		LOG.debug(sql);
		
		connect();
		
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setString(1, userName);
			res = prpstm.executeQuery();
			if (res != null && res.next()) {
				return new UserObject( res.getInt("id"), 
										res.getString("user_name"),
										res.getString("password"), 
										res.getString("salt"), 
										UserRoleEnum.valueOf(res.getString("role")).getRoleValue(),
										res.getTimestamp("created"),
										res.getBoolean("validated"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
	}
	
	@Override
	public UserObject getUser(int userId) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER	+ " WHERE id = ?";
		
		LOG.debug(sql);
		
		connect();
		
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			res = prpstm.executeQuery();
			if (res != null && res.next()) {
				return new UserObject( res.getInt("id"), 
										res.getString("user_name"),
										res.getString("password"), 
										res.getString("salt"), 
										UserRoleEnum.valueOf(res.getString("role")).getRoleValue(),
										res.getTimestamp("created"),
										res.getBoolean("validated"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
	}

	@Override
	public UserObject updateUser(UserObject userObject) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_USER + " SET "
				+ " user_name = ?, "
				+ " password = ?, "
				+ " salt = ?, "
				+ " role = ?, "
				+ " validated = ? "
				+ " WHERE id = ?";
		
		connect();
		
		LOG.debug("In updateUser with sql: " + sql);
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setString(1, userObject.getUserName());
			prpstm.setString(2, userObject.getPassword());
			prpstm.setString(3, userObject.getSalt());
			prpstm.setString(4, UserRoleEnum.getUserRoleEnum(userObject.getRole()).name());
			prpstm.setBoolean(5, userObject.isValidated());
			prpstm.setInt(6, userObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return userObject;
	}


	@Override
	public UserObject deleteUser(UserObject userObject) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER + " WHERE "
				+ " id = ?";
		
		connect();
		
		LOG.debug("In deleteUser with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return userObject;
	}

	
	@Override
	public void validateUser(String username) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_USER + " SET "
				+ " validated = ? "
				+ " WHERE user_name = ?";
		
		connect();
		
		
		LOG.debug("In validateUser with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setBoolean(1, true);
			prpstm.setString(2, username);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return;
		} finally {
			closeAll();
		}
		return;
	}
	
	//----------------------USERMETA METHODS----------------------
	

	@Override
	public UserMetaListObject getUsersMeta(){
		LOG.debug("in getUsersMeta");
		UserMetaListObject userMeta = new UserMetaListObject();
		connect();		
		//res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_META);
		ResultSet rs = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_META);
		try {
			while (rs!= null && rs.next()) {
				userMeta.add(new UserMetaObject(
						rs.getInt("user_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("phone_number"),
						rs.getDate("birthday"),
						rs.getString("home_town"),
						rs.getString("profession")));
			} 
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
			try {
				if(rs != null && !rs.isClosed()){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userMeta;
	}
	
	@Override
	public UserMetaObject getUserMeta(int userId){
		LOG.debug("in getUserMeta for user :" + userId);
		UserMetaObject userMeta = null;
		connect();		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " WHERE user_id = " + userId);
		try {
			if (res!= null && res.next()) {
				userMeta = new UserMetaObject(
						res.getInt("user_id"),
						res.getString("first_name"),
						res.getString("last_name"),
						res.getString("phone_number"),
						res.getDate("birthday"),
						res.getString("home_town"),
						res.getString("profession"));
			} 
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll("getUserMeta");
		}
		
		return userMeta;
	}
	
	@Override
	public boolean addUserMeta(UserMetaObject usermetaObject){
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " "
				+ "(user_id, first_name, last_name, phone_number, birthday, home_town, profession) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		LOG.debug("In addUserMeta with sql: " + sql);
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, usermetaObject.getUserId());
			prpstm.setString(2, usermetaObject.getFirstName());
			prpstm.setString(3, usermetaObject.getLastName());
			prpstm.setString(4, usermetaObject.getPhoneNumber());
			prpstm.setDate(5,  new java.sql.Date(usermetaObject.getBirthday().getTime()));
			prpstm.setString(6, usermetaObject.getHomeTown());
			prpstm.setString(7, usermetaObject.getProfession());
			
			prpstm.execute();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			closeAll("addUserMeta");
		}
		return true;
	}
	
	@Override
	public UserMetaObject updateUserMeta(UserMetaObject userMetaObject) {
		
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + 
				" SET first_name=?, last_name=?, phone_number=?, birthday=?, home_town=?, profession=? WHERE user_id=?";
		
		connect();
		
		LOG.debug("In updateUserMeta with sql: " + sql);

		try {
			LOG.debug("1");
			this.prpstm = this.conn.prepareStatement(sql);

			prpstm.setString(1, userMetaObject.getFirstName());
			prpstm.setString(2, userMetaObject.getLastName());
			prpstm.setString(3, userMetaObject.getPhoneNumber());
			prpstm.setDate(4,  new java.sql.Date(userMetaObject.getBirthday().getTime()));
			prpstm.setString(5, userMetaObject.getHomeTown());
			prpstm.setString(6, userMetaObject.getProfession());
			prpstm.setInt(7, userMetaObject.getUserId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			LOG.error(e.getStackTrace().toString());
			return null;
		} finally {
			closeAll("updateUserMeta");
		}
		return userMetaObject;
	}
	
	
/*	String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " VALUES (?,?,?,?,?,?,?) "
			+ "ON DUPLICATE KEY UPDATE "
			+ "first_name = ?, "
			+ "last_name = ?, "
			+ "phone_number = ?, "
			+ "birthday = ?, "
			+ "home_town = ?, "
			+ "profession = ? ";
	
	connect();
	
	LOG.debug("In updateUserMeta with sql: " + sql);

	try {
		prpstm = conn.prepareStatement(sql);
		prpstm.setInt(1, userMetaObject.getUserId());
		prpstm.setString(2, userMetaObject.getFirstName());
		prpstm.setString(3, userMetaObject.getLastName());
		prpstm.setString(4, userMetaObject.getPhoneNumber());
		prpstm.setDate(5, new Date(userMetaObject.getBirthday().getTime()));
		prpstm.setString(6, userMetaObject.getHomeTown());
		prpstm.setString(7, userMetaObject.getProfession());
		
		prpstm.setString(8, userMetaObject.getFirstName());
		prpstm.setString(9, userMetaObject.getLastName());
		prpstm.setString(10, userMetaObject.getPhoneNumber());
		prpstm.setDate(11, new Date(userMetaObject.getBirthday().getTime()));
		prpstm.setString(12, userMetaObject.getHomeTown());
		prpstm.setString(13, userMetaObject.getProfession());
*/

	@Override
	public boolean deleteUserMeta(int userId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_META + " WHERE "
				+ " user_id = ?";
		
		connect();
		
		LOG.debug("In deleteUserMeta with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			closeAll();
		}
		return true;
	}
	
	//----------------------USERLOG METHODS----------------------
	
	@Override
	public void loggUserLogin(int userId) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN + " "
				+ "(user_id) "
				+ "VALUES (?)";
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		return;
	}

	@Override
	public UserLoginLogListObject getUserLogins(int user_id) {
		UserLoginLogListObject userLogins = new UserLoginLogListObject();
		
		connect();
		
		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN + " WHERE user_id = " + user_id);
		try {
			while (res.next()) {
				userLogins.add(new UserLoginLogObject(res.getInt("id"), res.getInt("user_id"), res.getTimestamp("date")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		return userLogins;
	}
	
	@Override
	public UserLoginLogListObject getUserLogins() {
		UserLoginLogListObject userLogins = new UserLoginLogListObject();
		
		connect();
		
		
		res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN);
		try {
			while (res.next()) {
				userLogins.add(new UserLoginLogObject(res.getInt("id"), res.getInt("user_id"), res.getTimestamp("date")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
		}
		return userLogins;
	}
	

	@Override
	public boolean deleteUserLogsByUser(int userId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN + " WHERE "
				+ " user_id = ?";
		
		LOG.debug("In deleteUserLogsByUser with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			closeAll();
		}
		return true;
	
	}
	
	//----------------------USER REQUEST METHODS----------------------
	
	@Override
	public boolean addUserRoleRequest(int userId, UserRoleEnum role) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " "
				+ "(user_id, requested_role) "
				+ "VALUES (?, ?)";
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			prpstm.setString(2, role.name());
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			closeAll();
		}
		return true;
	}

	@Override
	public UserRoleRequestListObject getUserRoleRequests() {
		UserRoleRequestListObject userRequests = new UserRoleRequestListObject();
		
		connect();
		
		
		//res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST);
		ResultSet rs = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST);
		try {
			while (rs.next()) {
				userRequests.add(new UserRoleRequestObject(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getString("requested_role"), 
						rs.getTimestamp("date"), 
						rs.getBoolean("accepted")));
			} 
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			closeAll();
			try {
				if(rs != null && !rs.isClosed()){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userRequests;
	}

	@Override
	public boolean grantUserRoleRequest(int requestId) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " SET "
				+ " accepted = ? "
				+ " WHERE id = ?";
		LOG.debug("In updateUser with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setBoolean(1,true);
			prpstm.setInt(2, requestId);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			closeAll();
		}
		return true;
	}

	@Override
	public UserRoleRequestObject deleteUserRoleRequest(UserRoleRequestObject userRoleRequestObject) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " WHERE "
				+ " id = ?";
		
		LOG.debug("In deleteUserRoleRequest with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userRoleRequestObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			closeAll();
		}
		return userRoleRequestObject;
	}


	@Override
	public boolean deleteUserRoleRequestByUser(int userId) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " WHERE "
				+ " user_id = ?";
		
		LOG.debug("In deleteUserRoleRequestByUser with sql: " + sql);
		
		connect();
		
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			closeAll();
		}
		return true;
	
	}

}
