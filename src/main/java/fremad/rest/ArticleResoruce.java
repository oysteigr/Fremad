package fremad.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import fremad.domain.MatchReportObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.MatchReportListObject;
import fremad.domain.list.PageListObject;
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
	
	@RequestMapping("/getArticleHeaders")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleListObject getArticleHeaders() {
		LOG.debug("Getting article headers");
		return articleProcessor.getArticleHeaders();
	}
	
	@RequestMapping("/getArticleHeadersShort")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleListObject getArticleHeadersShort() {
		LOG.debug("Getting article headers short");
		return articleProcessor.getArticleHeadersShort();
	}
	
	@RequestMapping("/getArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject getArticle(@RequestBody String articleId) {
		LOG.debug("Getting articles of type: " + articleId);
		return articleProcessor.getArticle(Integer.parseInt(articleId));
	}

	@RequestMapping("/addArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject addArticle(@RequestBody ArticleObject article) {
		LOG.debug("Adding article ' " + article.getHeader() + "'");
		return articleProcessor.addArticle(article);
	}

	@RequestMapping("/updateArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject updateArticle(@RequestBody ArticleObject article) {
		LOG.debug("Updating article ' " + article.getHeader() + "'");
		return articleProcessor.updateArticle(article);
	}

	@RequestMapping("/deleteArticle")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject deleteArticle(@RequestBody ArticleObject article) {
		LOG.debug("Deleting article ' " + article.getHeader() + "'");
		return articleProcessor.deleteArticle(article);
	}
	
	@RequestMapping("/getArticleFromUrl")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArticleObject getArticleFromUrl(@RequestBody String url){
		LOG.debug("Getting article from url: ' " + url + "'");
		return articleProcessor.getArticleFromUrl(url);
	}
	
	@RequestMapping("/getPages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PageListObject getPages(){
		LOG.debug("Getting pages");
		return articleProcessor.getPages();
	}
	
	@RequestMapping("/getPublishedPages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PageListObject getPublishedPages(){
		LOG.debug("Getting pages");
		return articleProcessor.getPublishedPages();
	}
	
	@RequestMapping("/addPage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PageObject addPage(@RequestBody PageObject page){
		LOG.debug("Adding page: " + page.getArticleTitle());
		return articleProcessor.addPage(page);
	}
	
	@RequestMapping("/updatePage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  PageObject updatePage(@RequestBody PageObject page){
		LOG.debug("Updating page: " + page.getArticleTitle());
		return articleProcessor.updatePage(page);
	}
	
	@RequestMapping("/getMatchReports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MatchReportListObject getMatchReports(){
		return articleProcessor.getMatchReports();
	}
	
	@RequestMapping("/getMatchReport")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchReportObject getMatchReport(@RequestBody String articleId){
		return articleProcessor.getMatchReport(Integer.parseInt(articleId));
	}
	
	@RequestMapping("/addMatchReport")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MatchReportObject addMatchReport(@RequestBody MatchReportObject report){
		return articleProcessor.addMatchReport(report);
	}
	
	@RequestMapping("/updateMatchReport")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public  MatchReportObject updateMatchReport(@RequestBody MatchReportObject report){
		return articleProcessor.updateMatchReport(report);
	}
}
