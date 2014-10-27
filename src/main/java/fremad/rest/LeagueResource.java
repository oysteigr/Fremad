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

import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.processor.LeagueProcessor;

 

@RestController
@Scope("request")
@RequestMapping("/league")
public class LeagueResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(LeagueResource.class);
	
	@Autowired
	LeagueProcessor leagueProcessor;
	
	@RequestMapping("/addLeague")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LeagueObject addLeague(@RequestBody LeagueObject league){
		LOG.debug("Adding league ' " + league.getTeam() + "'");
		return leagueProcessor.addLeague(league);
	}
	
	@RequestMapping("/updateLeague")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateLeague(@RequestBody LeagueObject league){

	}
	
	@RequestMapping("/deleteLeague")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteTeam(@RequestBody LeagueObject league){

	}
	
	@RequestMapping("/getLeagues")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LeagueListObject getLeagues() {
		LOG.debug("Getting leagues..");

		return leagueProcessor.getLeagues();
	}
}