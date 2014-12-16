package fremad.rest;

import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.user.UserLogonObject;
import fremad.domain.user.UserObject;
import fremad.domain.user.UserRoleEnum;
import fremad.processor.UserProcessor;

@RestController
@Scope("request")
@RequestMapping("/user")
public class UserResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserProcessor userProcessor;

	@RequestMapping("/createUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean createUser(@RequestBody UserObject userObject){
		LOG.debug("Create user ' " + userObject.getUserName() + "'");
		return userProcessor.createUser(userObject);
	}
	
	@RequestMapping("/loginUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int loginUser(@RequestBody UserLogonObject userLogonObject){
		LOG.debug("Login user ' " + userLogonObject.getUserName() + "'");
		return userProcessor.loginUser(userLogonObject).getRoleValue();
	}
	
	@RequestMapping("/logoutUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean logout(){
		return userProcessor.logoutUser();
	}
	
	@RequestMapping("/getUserRole")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int getUserRole(){
		LOG.debug("getUserRole");
		return userProcessor.getUserRole().getRoleValue();
	}
}
