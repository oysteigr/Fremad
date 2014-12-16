package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.EventObject;
import fremad.domain.list.EventListObject;
import fremad.service.EventService;

@Component
@Scope("request")
public class EventProcessor {
	
	@Autowired
	EventService eventService;
	
	public EventListObject getEvents(int matchId){
		return eventService.getEvents(matchId);
	}

	public EventObject addEvent(EventObject event){
		return eventService.addEvent(event);
	}
	
	public EventObject updateEvent(EventObject event){
		return eventService.updateEvent(event);
	}
	
	public EventObject deleteEvent(EventObject event){
		return eventService.deleteEvent(event);
	}
}