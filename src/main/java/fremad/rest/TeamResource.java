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

import fremad.domain.TeamObject;
import fremad.domain.list.TeamListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.processor.TeamProcessor;
import fremad.utils.UrlParser;

 

@RestController
@Scope("request")
@Resource
@RequestMapping("/team")
public class TeamResource {
	
	@Autowired
	TeamProcessor teamProcessor;
	
	private static final Logger LOG = LoggerFactory.getLogger(TeamResource.class);
	
	@RequestMapping("/getTeams")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TeamListObject getTeams() {
		return teamProcessor.getTeams();
	}
	
	@RequestMapping("/getTeam")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TeamObject getTeam(@RequestBody String id) {
		LOG.debug("Looking for team with id= " + id);
		TeamObject response = teamProcessor.getTeam(Integer.parseInt(id));
		LOG.debug("Found : " + response.toString());
		return response;
	}
		
	@RequestMapping("/addTeam")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  TeamObject addTeam(@RequestBody TeamObject team){
		LOG.debug("Adding team ' " + team.getName() + "'");		
		return teamProcessor.addTeam(team);
	}
	
	@RequestMapping("/updateTeam")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TeamObject updateTeam(@RequestBody TeamObject team){
		return teamProcessor.updateTeam(team);

	}
	
	@RequestMapping("/deleteTeam")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TeamObject deleteTeam(@RequestBody TeamObject team){
		return teamProcessor.deleteTeam(team);

	}

	@RequestMapping("/getNameFromId")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getNameFromId(@RequestBody String id) {
		LOG.debug("Getting name from id= " + id);
		String name = UrlParser.getTeamNameFromId(Integer.parseInt(id));
		LOG.debug("Getting name from id= " + Integer.parseInt(id) + " and got name = " + name);
		return name;
	}

}