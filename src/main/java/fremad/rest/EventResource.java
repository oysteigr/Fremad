package fremad.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.EventObject;
import fremad.domain.list.EventListObject;
import fremad.processor.EventProcessor;

 

@RestController
@Scope("request")
@RequestMapping("/event")
public class EventResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventResource.class);
	
	@Autowired
	EventProcessor eventProcessor;

	@RequestMapping("/getEvents")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EventListObject getEvents(@RequestBody String matchId) {
		LOG.debug("Getting leagues..");

		return eventProcessor.getEvents(Integer.parseInt(matchId));
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