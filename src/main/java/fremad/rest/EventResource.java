package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.EventListObject;
import fremad.domain.EventObject;
import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.processor.EventProcessor;
import fremad.processor.LeagueProcessor;
import fremad.utils.UrlParser;

 

@RestController
@Scope("request")
@RequestMapping("/event")
public class EventResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventResource.class);
	
	@Autowired
	EventProcessor eventProcessor;

	@RequestMapping("/getEvents")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public EventListObject getEvents(int matchId) {
		LOG.debug("Getting leagues..");

		return eventProcessor.getEvents(matchId);
	}
	
	@RequestMapping("/addEvent")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EventObject addEvent(@RequestBody EventObject event){
		LOG.debug("Adding event of type ' " + event.getEventType() + "'");
		return eventProcessor.addEvent(event);
	}

	@RequestMapping("/updateEvent")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EventObject updateEvent(@RequestBody EventObject event){
		return eventProcessor.updateEvent(event);
	}

	@RequestMapping("/deleteEvent")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EventObject deleteEvent(@RequestBody EventObject event){
		return eventProcessor.deleteEvent(event);
	}

}