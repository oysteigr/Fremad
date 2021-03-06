package fremad.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcUserDao;
import fremad.domain.list.UserLoginLogListObject;
import fremad.domain.user.UserListObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;
import fremad.domain.user.UserRoleRequestObject;

@Service
@Scope("singleton")
public class UserService {
//	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private JdbcUserDao userDao;
	
	// ----------------------USER----------------------
	
	public UserListObject getUsers(){
		return cleanUserList(userDao.getUsers());
	}	
	public int addUser(UserObject userObject) throws SQLException{
		return userDao.addUser(userObject);
	}
	public UserObject getUser(String userName){
		return userDao.getUser(userName);
	}
	public UserObject getUser(int userId){
		return userDao.getUser(userId);
	}
	public UserObject updateUser(UserObject userObject){
		return userDao.updateUser(userObject);
	}
	public UserObject updateUserRole(UserObject userObject){
		return userDao.updateUserRole(userObject);
	}
	public UserObject deleteUser(UserObject userObject){
		return userDao.deleteUser(userObject);
	}
	public void validateUser(int id){
		userDao.validateUser(id);
		return;
	}
	
	// ----------------------USER META----------------------
	
	public UserMetaListObject getUsersMeta(){
		return userDao.getUsersMeta();
	}
	public UserMetaObject getUserMeta(int userId){
		return userDao.getUserMeta(userId);
	}
	public boolean addUserMeta(UserMetaObject userMetaObject){
		return userDao.addUserMeta(userMetaObject);
	}
	public UserMetaObject updateUserMeta(UserMetaObject userMetaObject){
		return userDao.updateUserMeta(userMetaObject);
	}
	public boolean deleteUserMeta(int userId){
		return userDao.deleteUserMeta(userId);
	}
	
	// ----------------------USER LOGS----------------------
	
	public void loggUserLogin(int userId){
		userDao.loggUserLogin(userId);
		return;
	}
	public UserLoginLogListObject getUserLogins(int userId){
		return userDao.getUserLogins(userId);
	}
	public UserLoginLogListObject getUserLogins(){
		return userDao.getUserLogins();
	}
	public boolean deleteUserLogsByUser(int userId){
		return userDao.deleteUserLogsByUser(userId);
	}	
	
	// ----------------------USER REQUESTS----------------------
	
	public boolean addUserRoleRequest(int userId, UserRoleEnum role){
		return userDao.addUserRoleRequest(userId, role);
	}
	public UserRoleRequestListObject getUserRoleRequests (){
		return userDao.getUserRoleRequests();
	}
	public boolean grantUserRoleRequest(int requestId){
		return userDao.grantUserRoleRequest(requestId);
	}
	public UserRoleRequestObject deleteUserRoleRequest(UserRoleRequestObject userRoleRequestObject){
		return userDao.deleteUserRoleRequest(userRoleRequestObject);
	}
	public boolean deleteUserRoleRequestByUser(int userId){
		return userDao.deleteUserRoleRequestByUser(userId);
	}
	
	private UserListObject cleanUserList(UserListObject userListObject){
		for(UserObject user : userListObject){
			user.setSalt("");
			user.setPassword("");
		}
		return userListObject;
	}
	
	//----------------------USER VALIDATION METHODS----------------------
	
	public boolean saveValidationCode(String code, int userId) {
		return userDao.saveValidationCode(code, userId);
	}
	
	public String getValidationCode(int userId) {
		return userDao.getValidationCode(userId);
	}
	
	public boolean deleteValidationCode(int userId) {
		return userDao.deleteValidationCode(userId);
	}
	
	//----------------------USER FORGOT PASSWORD METHODS----------------------
	
	public boolean saveForgotPasswordCode(String code, int userId) {
		return userDao.saveForgotPasswordCode(code, userId);
	}
	
	public String getForgotPasswordCode(int userId) {
		return userDao.getForgotPasswordCode(userId);
	}
	
	public boolean deleteForgotPasswordCode(int userId) {
		return userDao.deleteForgotPasswordCode(userId);
	}
}

