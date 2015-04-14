package fremad.dao;

import fremad.domain.BugObject;
import fremad.domain.FeatureRequestObject;
import fremad.domain.list.BugListObject;
import fremad.domain.list.FeatureRequestListObject;

public interface TechnicalDao {
	BugListObject getBugs();
	BugObject addBug(BugObject bugObject);
	BugObject updateBug(BugObject bugObject);
	BugObject deleteBug(BugObject bugObject);
	
	FeatureRequestListObject getFeatureRequests();
	FeatureRequestObject addFeatureRequest(FeatureRequestObject featureRequestObject);
	FeatureRequestObject updateFeatureRequest(FeatureRequestObject featureRequestObject);
	FeatureRequestObject deleteFeatureRequest(FeatureRequestObject featureRequestObject);
}
