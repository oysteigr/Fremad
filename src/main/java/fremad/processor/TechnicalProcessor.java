package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.BugObject;
import fremad.domain.list.BugListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.TechnicalService;

@Component
@Scope("request")
public class TechnicalProcessor {
	
	@Autowired
	private TechnicalService technicalService;
	
	@Autowired
	private SessionSecurityContext securityContext;

	
	public BugListObject getBugs(){
		
		securityContext.checkUserPremission(UserRoleEnum.PLAYER);
		
		return technicalService.getBugs();
	}
	public BugObject addBug(BugObject bugObject){
		
		bugObject.setUserId(securityContext.getUserId());
		
		securityContext.checkUserPremission(UserRoleEnum.PLAYER);
		
		return technicalService.addBug(bugObject);
	}
	public BugObject updateBug(BugObject bugObject){
		
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return technicalService.updateBug(bugObject);
	}
	public BugObject deleteBug(BugObject bugObject){
		
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return technicalService.deleteBug(bugObject);
	}


}
