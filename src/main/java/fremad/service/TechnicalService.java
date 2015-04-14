package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.TechnicalDao;
import fremad.domain.BugObject;
import fremad.domain.FeatureRequestObject;
import fremad.domain.list.BugListObject;
import fremad.domain.list.FeatureRequestListObject;

@Service
@Scope("singleton")
public class TechnicalService {
	
	@Autowired
	private TechnicalDao technicalDao;
	
	public BugListObject getBugs(){
		return technicalDao.getBugs();
	}
	public BugObject addBug(BugObject bugObject){
		return technicalDao.addBug(bugObject);
	}
	public BugObject updateBug(BugObject bugObject){
		return technicalDao.updateBug(bugObject);
	}
	public BugObject deleteBug(BugObject bugObject){
		return technicalDao.deleteBug(bugObject);
	}
	
	public FeatureRequestListObject getFeatureRequests(){
		return technicalDao.getFeatureRequests();
	}
	public FeatureRequestObject addFeatureRequest(FeatureRequestObject featureRequestObject){
		return technicalDao.addFeatureRequest(featureRequestObject);
	}
	public FeatureRequestObject updateFeatureRequest(FeatureRequestObject featureRequestObject){
		return technicalDao.updateFeatureRequest(featureRequestObject);
	}
	public FeatureRequestObject deleteFeatureRequest(FeatureRequestObject featureRequestObject){
		return technicalDao.deleteFeatureRequest(featureRequestObject);
	}
}	