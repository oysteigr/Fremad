package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.dao.JdbcLeagueDao;
import fremad.dao.LeagueDao;
import fremad.domain.LeagueObject;

 

@RestController
@Scope("request")
@RequestMapping("/league")
public class LeagueResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(LeagueResource.class);
	
	@RequestMapping("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void add(LeagueObject league){
		LeagueDao dao = new JdbcLeagueDao();
		LOG.debug("Adding league ' " + league.getTeam() + "'");
		dao.addLeague(league);
	}

}