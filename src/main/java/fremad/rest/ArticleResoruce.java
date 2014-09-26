package fremad.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.ArticleObject;
import fremad.processor.ArticleProcessor;

@RestController
@Scope("request")
@RequestMapping("/article")
public class ArticleResoruce {
	
	@Autowired
	private ArticleProcessor articleProcessor; 
	
	@RequestMapping("/add")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String AddDummy() throws Exception{
		fremad.dao.ArticleDao dao = new fremad.dao.ArticleDao();
		String msg = dao.insertArticle(null);
		
		return "<p>"+msg+"</p>";
	}
	
	@RequestMapping("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArticleObject GetDummy(){

		ArticleObject response = articleProcessor.getArticle(0);
		return response;
	}
}
