package fremad.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fremad.domain.ArticleListObject;
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
	
	@RequestMapping("/getList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArticleListObject GetList(){

		ArticleObject obj1 = new ArticleObject();
		ArticleObject obj2 = new ArticleObject();
		ArticleObject obj3 = new ArticleObject();
		ArticleObject obj4 = new ArticleObject();
		
		obj1.setArticleType("NEWS");
		obj1.setContent("");
		obj1.setContext("");
		obj1.setDate(new Date(1410739200));
		obj1.setHeader("Fremad klare for femtedivisjon!");
		obj1.setImageURL("/images/feiring.jpg");
		obj1.setAuthorId(3);
		
		obj2.setArticleType("MATCH");
		obj2.setContent("");
		obj2.setContext("");
		obj2.setDate(new Date(1409875200));
		obj2.setHeader("READY 2 - FREMAD FAMAGUSTA 3-2");
		obj2.setImageURL("/images/kamp1.jpg");
		obj2.setAuthorId(3);
		
		obj3.setArticleType("MATCH");
		obj3.setContent("");
		obj3.setContext("");
		obj3.setDate(new Date(1409270400));
		obj3.setHeader("Romsås 2 - Fremad Famagusta 2 7-1");
		obj3.setImageURL("/images/kamp2.jpg");
		obj3.setAuthorId(3);
		
		obj4.setArticleType("NEWS");
		obj4.setContent("");
		obj4.setContext("");
		obj4.setDate(new Date(1408838400));
		obj4.setHeader("Fremad-toget ruller videre!");
		obj4.setImageURL("/images/feiring.jpg");
		obj4.setAuthorId(3);
		
		ArticleListObject list = new ArticleListObject();
		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		list.add(obj4);
		
		
		return list;
	}
}
