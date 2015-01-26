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

import fremad.domain.PlayerObject;
import fremad.domain.list.PlayerListObject;
import fremad.processor.PlayerProcessor;

 

@RestController
@Scope("request")
@RequestMapping("/player")
public class PlayerResource {
	private static final Logger LOG = LoggerFactory.getLogger(PlayerResource.class);
	
	@Autowired
	PlayerProcessor playerProcessor;

	@RequestMapping("/getPlayers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PlayerListObject getPlayers() {
		LOG.debug("Getting players..");

		return playerProcessor.getPlayers();
	}
	
	@RequestMapping("/getPlayersByTeam")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PlayerListObject getPlayersByTeam(@RequestBody String teamId) {
		LOG.debug("Getting players from team: " + teamId);

		return playerProcessor.getPlayersByTeam(Integer.parseInt(teamId));
	}

	@RequestMapping("/addPlayer")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PlayerObject addPlayer(@RequestBody PlayerObject player){
		return playerProcessor.addPlayer(player);
	}

	@RequestMapping("/updatePlayer")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PlayerObject updatePlayer(@RequestBody PlayerObject player){
		return playerProcessor.updatePlayer(player);
	}

	@RequestMapping("/deletePlayer")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PlayerObject deletePlayer(@RequestBody PlayerObject player){
		return playerProcessor.deletePlayer(player);
	}

}