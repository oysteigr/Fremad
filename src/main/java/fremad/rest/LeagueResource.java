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

import fremad.dao.JdbcLeagueDao;
import fremad.dao.JdbcTeamDao;
import fremad.dao.LeagueDao;
import fremad.dao.TeamDao;
import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.domain.TeamListObject;
import fremad.domain.TeamObject;

 

@RestController
@Scope("request")
@RequestMapping("/league")
public class LeagueResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(LeagueResource.class);
	
	@RequestMapping("/addLeague")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LeagueObject addLeague(@RequestBody LeagueObject league){
		LeagueDao dao = new JdbcLeagueDao();
		LOG.debug("Adding league ' " + league.getTeam() + "'");
		dao.addLeague(league);
		return league;
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
		LeagueListObject dummy = new LeagueListObject();
		LeagueObject dummyItem = new LeagueObject();
		dummyItem.setId(242424);
		dummyItem.setTeam(3);
		dummyItem.setYear(2014);
		dummy.add(dummyItem);
		dummyItem = new LeagueObject();
		dummyItem.setId(323232);
		dummyItem.setTeam(4);
		dummyItem.setYear(2014);
		dummy.add(dummyItem);

		return dummy;
	}
}