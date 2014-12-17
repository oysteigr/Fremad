package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcUserDao;
import fremad.domain.user.UserLoginLogListObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;

@Service
@Scope("singleton")
public class UserService {
	
	@Autowired
	private JdbcUserDao userDao;
	
	public int addUser(UserObject userObject){
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
	public UserObject deleteUser(UserObject userObject){
		return userDao.deleteUser(userObject);
	}
	
	public void validateUser(String username){
		userDao.validateUser(username);
		return;
	}
	
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
	
	public boolean addUserRoleRequest(int userId, UserRoleEnum role){
		return userDao.addUserRoleRequest(userId, role);
	}
	public UserRoleRequestListObject getUserRoleRequests (){
		return userDao.getUserRoleRequests();
	}
	public boolean grantUserRoleRequest(int id){
		return userDao.grantUserRoleRequest(id);
	}
	public boolean deleteUserRoleRequest(int id){
		return userDao.deleteUserRoleRequest(id);
	}
}
