package fremad.rest;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fremad.utils.UrlParser;
import fremad.domain.MatchObject;
import fremad.dao.MatchDao;
import fremad.dao.JdbcMatchDao;
 

@RestController
@Scope("request")
@RequestMapping("/match")
public class MatchResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(MatchResource.class);

	
	@RequestMapping("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MatchObject get(int id){
		MatchDao dao = new JdbcMatchDao();
		LOG.debug("Getting match " + id);
		return dao.getMatch(id);
	}

}