package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.BugObject;
import fremad.domain.FeatureRequestObject;
import fremad.domain.list.BugListObject;
import fremad.domain.list.FeatureRequestListObject;
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

	
	public FeatureRequestListObject getFeatureRequests(){
		securityContext.checkUserPremission(UserRoleEnum.PLAYER);
		
		return technicalService.getFeatureRequests();
	}
	public FeatureRequestObject addFeatureRequest(FeatureRequestObject featureRequestObject){
		featureRequestObject.setUserId(securityContext.getUserId());
		securityContext.checkUserPremission(UserRoleEnum.PLAYER);
		
		return technicalService.addFeatureRequest(featureRequestObject);
	}
	public FeatureRequestObject updateFeatureRequest(FeatureRequestObject featureRequestObject){
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return technicalService.updateFeatureRequest(featureRequestObject);
	}
	public FeatureRequestObject deleteFeatureRequest(FeatureRequestObject featureRequestObject){
		securityContext.checkUserPremission(UserRoleEnum.SUPER);
		
		return technicalService.deleteFeatureRequest(featureRequestObject);
	}

}
