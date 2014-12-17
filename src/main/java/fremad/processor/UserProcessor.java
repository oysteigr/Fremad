package fremad.processor;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.user.UserLogonObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
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
	
	public boolean createUser(UserObject userObject){
		try{
			String[] params = PasswordManager.createHash(userObject.getPassword()).split(":");
			userObject.setPassword(params[2]);
			userObject.setSalt(params[1]);
			
			userService.addUser(userObject);
			
			
			//TODO: Move this!
			userService.validateUser(userObject.getUserName());
			
			if(UserRoleEnum.getUserRoleEnum(userObject.getRole()).getRoleValue() != UserRoleEnum.SUPPORTER.getRoleValue()){
				userService.addUserRoleRequest(userObject.getId(), UserRoleEnum.getUserRoleEnum(userObject.getRole()));
			}
				
		}catch(Exception e){
			LOG.debug(e.toString());
			return false;
		}
		return true; 
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
