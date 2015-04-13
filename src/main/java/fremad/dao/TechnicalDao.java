package fremad.dao;

import fremad.domain.BugObject;
import fremad.domain.list.BugListObject;

public interface TechnicalDao {
	BugListObject getBugs();
	BugObject addBug(BugObject bugObject);
	BugObject updateBug(BugObject bugObject);
	BugObject deleteBug(BugObject bugObject);
}
