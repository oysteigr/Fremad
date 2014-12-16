package fremad.dao;

import fremad.domain.EventObject;
import fremad.domain.list.EventListObject;

public interface EventDao {
	public EventListObject getEvents(int matchId);
	public EventObject addEvent(EventObject event);
	public EventObject updateEvent(EventObject event);
	public EventObject deleteEvent(EventObject event);
}
