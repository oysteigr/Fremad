package fremad.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import fremad.domain.user.UserLoginLogListObject;
import fremad.domain.user.UserLoginLogObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;
import fremad.domain.user.UserRoleRequestObject;

@Repository
public class JdbcUserDao extends JdbcConnection implements UserDao{
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcUserDao.class);

	@Override
	public int addUser(UserObject userObject) throws SQLException {
		LOG.debug("in addUser");
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER + " "
				+ "(user_name, password, salt) "
				+ "VALUES (?, ?, ?)";
		int key = -1;
		try {
			prpstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setString(1, userObject.getUserName());
			prpstm.setString(2, userObject.getPassword());
			prpstm.setString(3, userObject.getSalt());
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
			ResultSet rs = prpstm.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			throw new SQLException("Sql error");
		} finally {
			close();
		}
		return key;
	}

	@Override
	public UserObject getUser(String userName) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER	+ " WHERE user_name = ?";
		
		LOG.debug(sql);
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setString(1, userName);
			ResultSet res = prpstm.executeQuery();
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
			close();
		}
	}
	
	@Override
	public UserObject getUser(int userId) {
		String sql = "SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER	+ " WHERE id = ?";
		
		LOG.debug(sql);
		
		try {
			this.prpstm = this.conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			ResultSet res = prpstm.executeQuery();
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
			close();
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
		
		LOG.debug("In updateUser with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
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
			close();
		}
		return userObject;
	}


	@Override
	public UserObject deleteUser(UserObject userObject) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER + " WHERE "
				+ " id = ?";
		
		LOG.debug("In deleteUser with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userObject.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		} finally {
			close();
		}
		return userObject;
	}
	
	@Override
	public void validateUser(String username) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_USER + " SET "
				+ " validated = ? "
				+ " WHERE user_name = ?";
		
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
			close();
		}
		return;
	}
	@Override
	public void loggUserLogin(int userId) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN + " "
				+ "(user_id) "
				+ "VALUES (?)";
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, userId);
			
			LOG.debug("Executing: " + prpstm.toString());
			
			prpstm.execute();
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			close();
		}
		return;
	}

	@Override
	public UserLoginLogListObject getUserLogins(int user_id) {
		UserLoginLogListObject userLogins = new UserLoginLogListObject();
		
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN + " WHERE user_id = " + user_id);
		try {
			while (res.next()) {
				userLogins.add(new UserLoginLogObject(res.getInt("id"), res.getInt("user_id"), res.getTimestamp("date")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			close();
		}
		return userLogins;
	}
	
	@Override
	public UserLoginLogListObject getUserLogins() {
		UserLoginLogListObject userLogins = new UserLoginLogListObject();
		
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_LOGIN);
		try {
			while (res.next()) {
				userLogins.add(new UserLoginLogObject(res.getInt("id"), res.getInt("user_id"), res.getTimestamp("date")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			close();
		}
		return userLogins;
	}
	
	@Override
	public boolean addUserRoleRequest(int userId, UserRoleEnum role) {
		String sql = "INSERT INTO " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " "
				+ "(user_id, requested_role) "
				+ "VALUES (?, ?)";
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
			close();
		}
		return true;
	}

	@Override
	public UserRoleRequestListObject getUserRoleRequests() {
		UserRoleRequestListObject userRequests = new UserRoleRequestListObject();
		
		ResultSet res = select("SELECT * FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST);
		try {
			while (res.next()) {
				userRequests.add(new UserRoleRequestObject(res.getInt("id"), res.getInt("user_id"), 
						res.getString("requestedRole"), res.getTimestamp("date"), res.getBoolean("validated")));
			} 
		} catch (SQLException e) {
			LOG.error(e.toString());
		} finally {
			close();
		}
		return userRequests;
	}

	@Override
	public boolean grantUserRoleRequest(int id) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " SET "
				+ " accepted = ? "
				+ " WHERE id = ?";
		LOG.debug("In updateUser with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setBoolean(1,true);
			prpstm.setInt(2, id);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			close();
		}
		return true;
	}

	@Override
	public boolean deleteUserRoleRequest(int id) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_USER_ROLE_REQUEST + " WHERE "
				+ " id = ?";
		
		LOG.debug("In deleteUserRoleRequest with sql: " + sql);
		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, id);
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
			close();
		}
		return true;
	}

}
