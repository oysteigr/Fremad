package fremad.gui;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RestController;

import fremad.domain.TestObject;



@Path("/front")
public class Front {
	
	private static final String api_version = "00.01.00"; //version of the api

	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Webbbb Service</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}
	
	@Path("/json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TestObject checkRest(){
		TestObject testObject = new TestObject();
		testObject.id = 2;
		testObject.text = "Hello you";
		
		return testObject;
	}
	
}