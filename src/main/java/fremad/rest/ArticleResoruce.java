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

import fremad.domain.ArticleObject;
import fremad.domain.list.ArticleListObject;
import fremad.processor.ArticleProcessor;

@RestController
@Scope("request")
@RequestMapping("/article")
public class ArticleResoruce {

	private static final Logger LOG = LoggerFactory
			.getLogger(ArticleResoruce.class);

	@Autowired
	private ArticleProcessor articleProcessor;

	@RequestMapping("/getArticles")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleListObject getArticles(@RequestBody String articleType) {
		LOG.debug("Getting articles of type: " + articleType);
		return articleProcessor.getArticles(articleType);
	}

	@RequestMapping("/addArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject addArticle(ArticleObject article) {
		LOG.debug("Adding article ' " + article.getHeader() + "'");
		return articleProcessor.addArticle(article);
	}

	@RequestMapping("/updateArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject updateArticle(ArticleObject article) {
		LOG.debug("Updating article ' " + article.getHeader() + "'");
		return articleProcessor.updateArticle(article);
	}

	@RequestMapping("/deleteArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject deleteArticle(ArticleObject article) {
		LOG.debug("Deleting article ' " + article.getHeader() + "'");
		return articleProcessor.deleteArticle(article);
	}
}
