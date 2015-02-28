package fremad.rest;


import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.MatchObject;
import fremad.domain.list.MatchListObject;
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
	public MatchListObject getMatches(@RequestBody String teamId){
		LOG.debug("Getting match " + teamId);
		return matchProcessor.getMatches(Integer.parseInt(teamId));
	}
	
	@RequestMapping("/getThisYearsMatches")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchListObject getThisYearsMatches(){
		LOG.debug("Getting matches for this year");
		return matchProcessor.getThisYearsMatches();
	}
	
	@RequestMapping("/getNextMatch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchObject getNextMatch(@RequestBody String teamId, @Context final HttpServletResponse response){
		LOG.debug("Getting next match " + teamId);
		MatchObject nextMatch = matchProcessor.getNextMatch(Integer.parseInt(teamId));
		
		if (nextMatch == null){
			response.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		}
		
		return nextMatch;
	}
	
	@RequestMapping("/getPrevMatch")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchObject getPrevMatch(@RequestBody String teamId, @Context final HttpServletResponse response){
		LOG.debug("Getting prev match " + teamId);
		MatchObject prevMatch = matchProcessor.getPrevMatch(Integer.parseInt(teamId));
		
		
		if (prevMatch == null){
			response.setStatus(Response.Status.NO_CONTENT.getStatusCode());
		}
		
		return prevMatch;
	}

}