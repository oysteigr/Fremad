package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.LeagueObject;
import fremad.domain.TableEntryListObject;
import fremad.domain.TableEntryObject;
import fremad.domain.TestObject;
import fremad.utils.UrlParser;
 

@RestController
@Scope("request")
@RequestMapping("/hello")
public class TestResource {

	private static final String api_version = "00.01.00"; //version of the api

	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	
	@GET
	@RequestMapping("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@RequestMapping("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	@RequestMapping("/json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TestObject checkRest(){
		TestObject testObject = new TestObject();
		testObject.id = 2;
		testObject.text = "Hello you";
		
		return testObject;
	}
	
	@RequestMapping("/table")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody TableEntryListObject getTable(){
		
		LeagueObject leagueObject = new LeagueObject();
		leagueObject.setId(138835);
		leagueObject.setTeam(30296);
		leagueObject.setYear(2014);
		
		TableEntryListObject tableEntryListObject = new TableEntryListObject();
//		tableEntryListObject.setLeagueObject(leagueObject);
		
		tableEntryListObject = UrlParser.getTableEntryListObject(leagueObject);
		
		return tableEntryListObject;
	}
	
	@RequestMapping("/tableitem")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody TableEntryObject getTableItem(){
		
		LeagueObject leagueObject = new LeagueObject();
		leagueObject.setId(138835);
		leagueObject.setTeam(30296);
		leagueObject.setYear(2014);
		
		TableEntryListObject tableEntryListObject = new TableEntryListObject();
//		tableEntryListObject.setLeagueObject(leagueObject);
		
		tableEntryListObject = UrlParser.getTableEntryListObject(leagueObject);
		
		return tableEntryListObject.get(1);
	}
}