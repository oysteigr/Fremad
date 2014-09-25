package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RestController;

import fremad.domain.TestObject;
 


@Path("/hello")
public class TestResource {

	private static final String api_version = "00.01.00"; //version of the api

	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
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
	
	@Path("/add")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String AddDummy(){
		fremad.dao.TestDao dao = new fremad.dao.TestDao();
		String msg = dao.insertArticle();
		
		return "<p>"+msg+"</p>";
	}
	
}