package fremad.security;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.user.UserRoleEnum;


@Component
@Scope("request")
public class SessionSecurityContext implements SecurityContext {
	
	private static String SESSION_USER_NAME = "sessionUserName";
	private static String SESSION_USER_ROLE = "sessionUserRole";
	
	private static final Logger LOG = LoggerFactory.getLogger(SecurityContext.class);

	
	@Autowired
	protected HttpServletRequest httpServletRequest;
	
	
	public <T> void setSessionAttribute(String attributeName, T attributeValue){
		
	}
	
	public void createSession(String userName){
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute(SESSION_USER_NAME, userName);
		LOG.debug("Session created: " + new Date(session.getCreationTime()).toString());
	}
	
	public void getSession(String userName){
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute(SESSION_USER_NAME, userName);
		LOG.debug("Session created: " + new Date(session.getCreationTime()).toString() + " for user " + session.getAttribute(SESSION_USER_NAME));
	}
	
	public void invalidateSession() throws Exception{
		HttpSession session = httpServletRequest.getSession(false);
		String userName;
		if(session != null){
			userName = (String) session.getAttribute(SESSION_USER_NAME);
			session.invalidate();
			session = httpServletRequest.getSession(false);
			if(session == null){
				LOG.debug("Session invalidated: " + new Date().toString() + " for user " + userName);
				return;
			}
		}
		throw new Exception("Session error");
		
	}
	
	public void setUserRole(UserRoleEnum userRole){
		HttpSession session = httpServletRequest.getSession(false);
		session.setAttribute(SESSION_USER_ROLE, userRole);
		if(session.getAttribute(SESSION_USER_ROLE).equals(userRole)){
			LOG.debug("User role " + userRole + " set for user " + session.getAttribute(SESSION_USER_NAME));
		}
	}
	
	public boolean checkUserPremission(UserRoleEnum premissionRole) throws Exception{
		HttpSession session = httpServletRequest.getSession(false);
		UserRoleEnum userRole = (UserRoleEnum) session.getAttribute(SESSION_USER_ROLE);
		if(premissionRole.getRoleValue() > userRole.getRoleValue()){
			throw new Exception("User have not premission to do this");
		}
		return true;
	}
	
	public UserRoleEnum getUserRole(){
		HttpSession session = httpServletRequest.getSession(false);
		if(session == null){
			LOG.debug("User has no session");
			return UserRoleEnum.UNAUTH;
		}

		return (UserRoleEnum) session.getAttribute(SESSION_USER_ROLE);
	}
	
	public String getUserName(){
		HttpSession session = httpServletRequest.getSession(false);
		if(session == null){
			LOG.debug("User has no session");
			return null;
		}

		return (String) session.getAttribute(SESSION_USER_NAME);
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		// TODO Auto-generated method stub
		return null;
	}
}
