package fremad.rest;

import javax.ws.rs.Consumes;
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

import fremad.domain.TableEntryListObject;
import fremad.domain.TableEntryObject;
import fremad.processor.TableEntryProcessor;

@RestController
@Scope("request")
@RequestMapping("/tableEntry")
public class TableEntryResource {

	
	private static final Logger LOG = LoggerFactory.getLogger(TableEntryResource.class);

	@Autowired
	TableEntryProcessor tableEntryProcessor;
	
	@RequestMapping("/getTableEntry")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TableEntryObject getTableEntry(@RequestBody String matchId){
		LOG.debug("Getting match " + matchId);
		return tableEntryProcessor.getTableEntry(Integer.parseInt(matchId));
	}
	
	@RequestMapping("/getTableEntries")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TableEntryListObject getTableEntries(@RequestBody String leagueId){
		LOG.debug("Getting match " + leagueId);
		return tableEntryProcessor.getTableEntries(Integer.parseInt(leagueId));
	}
}