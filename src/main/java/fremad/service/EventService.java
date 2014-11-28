package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcEventDao;
import fremad.domain.EventListObject;
import fremad.domain.EventObject;

@Service
public class EventService {
	
	@Autowired
	JdbcEventDao eventDao;
	
	public EventListObject getEvents(int matchId){
		return eventDao.getEvents(matchId);
	}

	public EventObject addEvent(EventObject event){
		return eventDao.addEvent(event);
	}

	public EventObject updateEvent(EventObject event){
		return eventDao.updateEvent(event);
	}

	public EventObject deleteEvent(EventObject event){
		return eventDao.deleteEvent(event);
	}

}
