package fremad.rest;


import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.user.UserListObject;
import fremad.domain.user.UserLogonObject;
import fremad.domain.user.UserMetaListObject;
import fremad.domain.user.UserMetaObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleRequestListObject;
import fremad.exception.AbstractRestException;
import fremad.processor.UserProcessor;

@RestController
@Scope("request")
@RequestMapping("/user")
public class UserResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserProcessor userProcessor;
	
	
	@RequestMapping("/getUsers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserListObject getUsers(){
		return userProcessor.getUsers();
	}
	
	@RequestMapping("/getUsersMeta")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserMetaListObject getUsersMeta(){
		return userProcessor.getUsersMeta();
	}
	
	@RequestMapping("/getUserMeta")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserMetaObject getUserMeta(@RequestBody String userId){
		LOG.debug("Getting userMeta for: " + userId);
		return userProcessor.getUserMeta(Integer.parseInt(userId));
	}

	@RequestMapping("/deleteUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserObject deleteUser(@RequestBody UserObject userObject, @Context final HttpServletResponse response){
		LOG.debug("Delete user ' " + userObject.getUserName() + "'");
		
		UserObject userResponse = userProcessor.deleteUser(userObject);
		
		if (userResponse == null){
			response.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		}
		
		return userResponse;
	}
	
	@RequestMapping("/createUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int createUser(@RequestBody UserObject userObject){
		LOG.debug("Create user ' " + userObject.getUserName() + "'");
		return userProcessor.createUser(userObject);
	}
	
	@RequestMapping("/addUserMeta")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addUserMeta(@RequestBody UserMetaObject userMetaObject, @Context final HttpServletResponse response){
		LOG.debug("Add userMeta ' " + userMetaObject.getUserId() + "'");
		if(!userProcessor.addUserMeta(userMetaObject)){
			response.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		}
	}
	
	@RequestMapping("/updateUserMeta")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserMetaObject updateUserMeta(@RequestBody UserMetaObject userMetaObject, @Context final HttpServletResponse response){
		LOG.debug("Update userMeta ' " + userMetaObject.getUserId() + "'");
		UserMetaObject userMetaResponse = userProcessor.updateUserMeta(userMetaObject);
		if(userMetaResponse == null){
			response.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		}
		return userMetaResponse;
	}
	
	@RequestMapping("/loginUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int loginUser(@RequestBody UserLogonObject userLogonObject) throws AbstractRestException{
//		throw new InputException(new Exception("dud"), 101, "Username allready taken");
		LOG.debug("Login user ' " + userLogonObject.getUserName() + "'");
		return userProcessor.loginUser(userLogonObject).getRoleValue();
	}
	
	@RequestMapping("/logoutUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean logout(){
		return userProcessor.logoutUser();
	}
	
	@RequestMapping("/getUserId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int getUserId(){
		LOG.debug("getUserId");
		return userProcessor.getUserId();
	}
	
	@RequestMapping("/getUserRole")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int getUserRole(){
		LOG.debug("getUserRole");
		return userProcessor.getUserRole().getRoleValue();
	}
	
	@RequestMapping("/getUserRoleRequests")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserRoleRequestListObject getUserRoleRequests(){
		LOG.debug("getUserRole");
		return userProcessor.getUserRoleRequests();
	}
	
	
}
