package fremad.rest;


import javax.annotation.Resource;
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

import fremad.domain.BugObject;
import fremad.domain.TeamObject;
import fremad.domain.list.BugListObject;
import fremad.domain.list.TeamListObject;
import fremad.processor.TeamProcessor;
import fremad.processor.TechnicalProcessor;
import fremad.utils.UrlParser;

 

@RestController
@Scope("request")
@Resource
@RequestMapping("/technical")
public class TechnicalResource {
	
	@Autowired
	TechnicalProcessor technicalProcessor;
	
	private static final Logger LOG = LoggerFactory.getLogger(TechnicalResource.class);
	
	@RequestMapping("/getBugs")
	@GET
	public BugListObject getBugs(){
		LOG.debug("In getBugs");
		return technicalProcessor.getBugs();
	}
	
	@RequestMapping("/addBug")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BugObject addBug(@RequestBody BugObject bugObject){
		LOG.debug("In addBug with id=" + bugObject.getId());
		return technicalProcessor.addBug(bugObject);
	}
	
	@RequestMapping("/updateBug")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BugObject updateBug(@RequestBody BugObject bugObject){
		LOG.debug("In updateBug with id=" + bugObject.getId());
		return technicalProcessor.updateBug(bugObject);
	}
	
	@RequestMapping("/deleteBug")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BugObject deleteBug(@RequestBody BugObject bugObject){
		LOG.debug("In deleteBug with id=" + bugObject.getId());
		return technicalProcessor.deleteBug(bugObject);
	}

}