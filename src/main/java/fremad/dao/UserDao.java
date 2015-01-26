package fremad.dao;

import java.sql.SQLException;

import fremad.domain.user.UserListObject;
import fremad.domain.user.UserLoginLogListObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;

public interface UserDao {
	
	UserListObject getUsers();
	int addUser(UserObject userObject) throws SQLException;
	UserObject getUser(String userName);
	UserObject getUser(int userId);
	UserObject updateUser(UserObject userObject);
	UserObject deleteUser(UserObject userObject);
	void validateUser(String username);
	
	UserMetaListObject getUsersMeta();
	UserMetaObject getUserMeta(int userId);
	boolean addUserMeta(UserMetaObject usermetaObject);
	UserMetaObject updateUserMeta(UserMetaObject usermetaObject);
	boolean deleteUserMeta(int userId);
	
	void loggUserLogin(int userId);
	UserLoginLogListObject getUserLogins(int user_id);
	UserLoginLogListObject getUserLogins();
	boolean deleteUserLogsByUser(int userId);
	
	boolean addUserRoleRequest(int userId, UserRoleEnum role);
	UserRoleRequestListObject getUserRoleRequests ();
	boolean grantUserRoleRequest(int id);
	boolean deleteUserRoleRequest(int id);
	boolean deleteUserRoleRequestByUser(int userId);

	
}
