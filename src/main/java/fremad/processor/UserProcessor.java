package fremad.processor;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.user.UserListObject;
import fremad.domain.user.UserLogonObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;
import fremad.domain.user.UserRoleRequestObject;
import fremad.exception.InputException;
import fremad.security.PasswordManager;
import fremad.security.SessionSecurityContext;
import fremad.service.UserService;

@Component
@Scope("request")	
public class UserProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserProcessor.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionSecurityContext securityContext;
	
	public UserListObject getUsers(){
		LOG.debug("in getUsers");
		return userService.getUsers();
		
	}
	
	public UserMetaListObject getUsersMeta(){
		LOG.debug("in getUserMeta");
		return userService.getUsersMeta();
	}
	
	public UserMetaObject getUserMeta(int userId){
		LOG.debug("in getUserMeta");
		if(userId == -1){
			userId = this.getUserId();
		}
		return userService.getUserMeta(userId);
	}
	
	public UserMetaObject updateUserMeta(UserMetaObject userMetaObject){
		if(userService.getUserMeta(userMetaObject.getUserId()) == null){
			LOG.debug("no current user in updateUserMeta");
			return userService.addUserMeta(userMetaObject) ? userMetaObject : null;
		}
		LOG.debug("found current user in updateUserMeta");
		return userService.updateUserMeta(userMetaObject);
	}
	
	public int createUser(UserObject userObject){
		LOG.debug("in createUser");
		int id = -1;
		try{
			String[] params = PasswordManager.createHash(userObject.getPassword()).split(":");
			userObject.setPassword(params[2]);
			userObject.setSalt(params[1]);
			
			id = userService.addUser(userObject);
			userObject.setId(id);
			
			//TODO: Move this!
			userService.validateUser(userObject.getUserName());
			
			if(UserRoleEnum.getUserRoleEnum(userObject.getRole()).getRoleValue() != UserRoleEnum.SUPPORTER.getRoleValue()){
				userService.addUserRoleRequest(userObject.getId(), UserRoleEnum.getUserRoleEnum(userObject.getRole()));
			}
				
		}catch(SQLException e){
			LOG.debug(e.toString());
			throw new InputException(e, 101, "Username allready taken");
		}catch(Exception e){
			LOG.debug(e.toString());
			return -1;
		}
		return id; 
	}
	
	public boolean addUserMeta(UserMetaObject userMetaObject){
		return userService.addUserMeta(userMetaObject);
	}
	
	public UserObject deleteUser(UserObject userObject){
		
		if(userService.deleteUserMeta(userObject.getId())){
			LOG.debug("Success on deleting userMeta of user: " + userObject.getId());
		}
		if(userService.deleteUserLogsByUser(userObject.getId())){
			LOG.debug("Success on deleting userLogs of user: " + userObject.getId());
		}
		if(userService.deleteUserRoleRequestByUser(userObject.getId())){
			LOG.debug("Success on deleting roleRequest of user: " + userObject.getId());
		}
		//TODO: article
		
		UserObject userResponse = userService.deleteUser(userObject);

		return userResponse;
	}
	
	public UserRoleEnum loginUser(UserLogonObject userLogonObject){
		
		UserObject registeredUser = userService.getUser(userLogonObject.getUserName());
		UserRoleEnum userRole = null;
		
		try{
			userIsValidated(registeredUser);
			checkPassword(userLogonObject, registeredUser);
			userService.loggUserLogin(registeredUser.getId());
			userRole = UserRoleEnum.getUserRoleEnum(registeredUser.getRole());
		}catch(Exception e){
			LOG.debug(e.toString());
			return null;
		}
		securityContext.createSession(registeredUser.getUserName());
		securityContext.setUserRole(userRole);
		
		LOG.debug("user " + registeredUser.getUserName() + " logged in with role " + userRole);
		return userRole; 
	}
	
	public boolean logoutUser(){
		try{
			securityContext.invalidateSession();
		}catch(Exception e){
			LOG.debug(e.toString());
			return false;
		}
		return true;
	}
	
	public UserRoleEnum getUserRole(){
		return securityContext.getUserRole();
	}
	
	public int getUserId() {
		int id = userService.getUser(securityContext.getUserName()).getId();
		LOG.debug("Got used id by session: " + id);
		return id;
	}
	
	public UserRoleRequestListObject getUserRoleRequests(){
		int userRole = getUserRole().getRoleValue();
		UserRoleRequestListObject response = userService.getUserRoleRequests();
		UserRoleRequestListObject filteredResponse = new UserRoleRequestListObject();
		for(UserRoleRequestObject request : response){
			if (request.getRequestedRole() < userRole){
				filteredResponse.add(request);
			}
		}
		return filteredResponse;
	}
	
	private void userIsValidated(UserObject userObject) throws Exception{
		if(!userObject.isValidated()){
			throw new Exception("Not validated");
		} 
	}
	
	private void checkPassword(UserLogonObject userLogonObject, UserObject registeredUser) 
			throws NoSuchAlgorithmException, InvalidKeySpecException, Exception{
		
		if(!PasswordManager.validatePassword(userLogonObject.getPassword(), 
				"1000:" + registeredUser.getSalt() + ":" + registeredUser.getPassword())){
			throw new Exception("wrong password!");
		}
		
	}


	
	
}
