package fremad.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("request")
@RequestMapping("/haidar")
public class HaidarRest {
	
	@RequestMapping("/hello")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<h1>HELLO HAIDAR</h1>";
	}

}
