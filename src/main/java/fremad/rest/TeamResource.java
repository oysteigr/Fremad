package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.dao.JdbcTeamDao;
import fremad.dao.TeamDao;
import fremad.domain.TeamObject;

 

@RestController
@Scope("request")
@RequestMapping("/team")
public class TeamResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(TeamResource.class);
	
	@RequestMapping("/add")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(TeamObject team){
		TeamDao dao = new JdbcTeamDao();
		LOG.debug("Adding team ' " + team.getName() + "'");
		dao.addTeam(team);
	}

}