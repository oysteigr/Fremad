package fremad.rest;


import java.util.List;

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

import fremad.domain.MatchListObject;
import fremad.domain.MatchObject;
import fremad.processor.MatchProcessor;
 

@RestController
@Scope("request")
@RequestMapping("/match")
public class MatchResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(MatchResource.class);

	@Autowired
	MatchProcessor matchProcessor;
	
	@RequestMapping("/getMatch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchObject getMatch(@RequestBody String matchId){
		LOG.debug("Getting match " + matchId);
		return matchProcessor.getMatch(Integer.parseInt(matchId));
	}
	
	@RequestMapping("/getMatches")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchListObject getMatches(@RequestBody String leagueId){
		LOG.debug("Getting match " + leagueId);
		return matchProcessor.getMatches(Integer.parseInt(leagueId));
	}

}