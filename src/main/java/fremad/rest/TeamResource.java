package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.dao.JdbcTeamDao;
import fremad.dao.TeamDao;
import fremad.domain.TeamListObject;
import fremad.domain.TeamObject;

 

@RestController
@Scope("request")
@RequestMapping("/team")
public class TeamResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(TeamResource.class);
	
	@RequestMapping("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TeamObject add(@RequestBody TeamObject team){
		TeamDao dao = new JdbcTeamDao();
		LOG.debug("Adding team ' " + team.getName() + "'");
		dao.addTeam(team);
		return team;
	}
	
	@RequestMapping("/getTeams")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TeamListObject getTeams() {
		TeamDao dao = new JdbcTeamDao();
		return dao.getTeams();
	}

}