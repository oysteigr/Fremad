package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.TechnicalDao;
import fremad.domain.BugObject;
import fremad.domain.list.BugListObject;

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

}	