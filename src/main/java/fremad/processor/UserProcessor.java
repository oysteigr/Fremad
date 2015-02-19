package fremad.processor;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import fremad.domain.list.UserLoginLogListObject;
import fremad.domain.user.UserListObject;
import fremad.domain.user.UserLogonObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserResetPassword;
import fremad.domain.user.UserRoleEnum;
import fremad.domain.user.UserRoleRequestListObject;
import fremad.domain.user.UserRoleRequestObject;
import fremad.exception.TechnicalErrorException;
import fremad.exception.UserExistsException;
import fremad.exception.UserNotFoundException;
import fremad.exception.UserNotValidatedException;
import fremad.exception.UserPasswordCombiException;
import fremad.exception.ValidationException;
import fremad.security.PasswordManager;
import fremad.security.SessionSecurityContext;
import fremad.service.UserService;
import fremad.tools.GmailSmtp;
import fremad.utils.MailGenerator;

@Component
@Scope("request")	
public class UserProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserProcessor.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionSecurityContext securityContext;
	
	@Autowired
	private GmailSmtp gmailSmtp;
	
	
	public UserListObject getUsers(){
		LOG.debug("in getUsers");
		
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return userService.getUsers();
		
	}
	
	public UserMetaListObject getUsersMeta(){
		LOG.debug("in getUserMeta");

		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return userService.getUsersMeta();
	}
	
	public UserMetaObject getUserMeta(int userId){
		LOG.debug("in getUserMeta");
		
		securityContext.checkUserPremission(UserRoleEnum.SUPPORTER);
		
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
			userObject.setValidated(false);
			
			id = userService.addUser(userObject);
			userObject.setId(id);
			
			sendAndCreateValidationCode(userObject);
			
			if(UserRoleEnum.getUserRoleEnum(userObject.getRole()).getRoleValue() != UserRoleEnum.SUPPORTER.getRoleValue()){
				userService.addUserRoleRequest(userObject.getId(), UserRoleEnum.getUserRoleEnum(userObject.getRole()));
			}
				
		}catch(SQLException e){
			LOG.debug(e.toString());
			throw new UserExistsException(e, 0, "Username allready taken");
		}catch(Exception e){
			LOG.debug(e.toString());
			return -1;
		}
		return id; 
	}
	
	public UserObject updateUserRole(UserObject userObject) {
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		return userService.updateUserRole(userObject);
	}
	
	public UserRoleEnum getUserRole(){
		return securityContext.getUserRole();
	}
	
	public boolean addUserMeta(UserMetaObject userMetaObject){
		return userService.addUserMeta(userMetaObject);
	}
	
	public UserObject deleteUser(UserObject userObject){
		
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
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
		
		UserObject registeredUser;
		try{
			registeredUser = userService.getUser(userLogonObject.getUserName());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException(null, 0, "User does not exist");
		}
		UserRoleEnum userRole = null;
		
		

		userIsValidated(registeredUser);
		checkPassword(userLogonObject.getPassword(), registeredUser);
		userService.loggUserLogin(registeredUser.getId());
		userRole = UserRoleEnum.getUserRoleEnum(registeredUser.getRole());

		securityContext.createSession(registeredUser.getUserName());
		securityContext.setUserRole(userRole);
		
		LOG.debug("user " + registeredUser.getUserName() + " logged in with role " + userRole);
		return userRole; 
	}
	
	public String changePassword(UserResetPassword userResetPassword) {
		UserObject registeredUser;
		try{
			registeredUser = userService.getUser(userResetPassword.getId());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException(null, 0, "User does not exist");
		}

		userIsValidated(registeredUser);
		checkPassword(userResetPassword.getOldPassword(), registeredUser);
		
		String[] params;
		try {
			params = PasswordManager.createHash(userResetPassword.getNewPassword()).split(":");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new TechnicalErrorException(e, 0, "Error on creating new password");
		}
		registeredUser.setPassword(params[2]);
		registeredUser.setSalt(params[1]);
		
		userService.updateUser(registeredUser);
		
		return "Succes on changing password";
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

	
	public int getUserId() {
		int id = userService.getUser(securityContext.getUserName()).getId();
		LOG.debug("Got used id by session: " + id);
		return id;
	}
	
	public UserRoleRequestListObject getUserRoleRequests(){

		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		UserRoleRequestListObject response = userService.getUserRoleRequests();

		return response;
	}
	
	public boolean grantUserRoleRequest(UserRoleRequestObject userRoleRequestObject) {

		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		UserObject userAsking = userService.getUser(userRoleRequestObject.getUserId());
		userAsking.setRole(userRoleRequestObject.getRequestedRole());
		if(userService.updateUser(userAsking) == null){
			return false;
		}
		return userService.grantUserRoleRequest(userRoleRequestObject.getId());
	}

	public UserRoleRequestObject deleteUserRoleRequest(UserRoleRequestObject userRoleRequestObject) {

		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return userService.deleteUserRoleRequest(userRoleRequestObject);
	}
	
	public boolean validateUser(String code) {
		LOG.debug("Got code: " + code);
		String validationCode = code.split("-")[0].trim();
		String idAsString = code.split("-")[1].trim();
		LOG.debug("Got id: " + idAsString);
		int userId = Integer.parseInt(idAsString);
		userService.getValidationCode(userId);
		String correctCode = userService.getValidationCode(userId);
		LOG.debug("Checking code: " + validationCode + "with correct code: " + correctCode);
		if(validationCode.equals(correctCode)){
			userService.validateUser(userId);
			userService.deleteValidationCode(userId);
			return true;
		}
		throw new ValidationException(null, 0, "Validation failed");
	}
	
	private void userIsValidated(UserObject userObject) throws UserNotValidatedException {
		if(!userObject.isValidated()){
			throw new UserNotValidatedException(null, 0,"Not validated");
		} 
	}
	
	private void checkPassword(String password, UserObject registeredUser) 
			throws UserPasswordCombiException{
		
		boolean correctPassword = false;
		try {
			
			correctPassword = PasswordManager.validatePassword(password, 
					"1000:" + registeredUser.getSalt() + ":" + registeredUser.getPassword());
			
			if(!correctPassword){
				throw new UserPasswordCombiException(null, 0,"wrong password!");
			}
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
			throw new TechnicalErrorException(null, 0,"internal error!");
		} catch (InvalidKeySpecException e) {			
			e.printStackTrace();
			throw new TechnicalErrorException(null, 0,"internal error!");
		}
		
	}
	
	private void sendAndCreateValidationCode(UserObject registeredUser) throws AddressException, MessagingException{
		String validationCode = generateValidationCode();
		
		userService.saveValidationCode(validationCode, registeredUser.getId());
		
		gmailSmtp.sendEmail(registeredUser.getUserName(), "Validation code", MailGenerator.generateValidationMail(registeredUser.getId(), validationCode));
		
	}

	private String generateValidationCode(){
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public UserLoginLogListObject getUserLogins() {
		
		securityContext.checkUserPremission(UserRoleEnum.SUPER);

		return userService.getUserLogins();
	}


}
