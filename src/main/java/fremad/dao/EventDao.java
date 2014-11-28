package fremad.dao;

import fremad.domain.EventListObject;
import fremad.domain.EventObject;

public interface EventDao {
	public EventListObject getEvents(int matchId);
	public EventObject addEvent(EventObject event);
	public EventObject updateEvent(EventObject event);
	public EventObject deleteEvent(EventObject event);
}
