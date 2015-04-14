package fremad.processor;

import java.util.Collections;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.ArticleObject;
import fremad.domain.MatchObject;
import fremad.domain.MatchReportObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.MatchReportListObject;
import fremad.domain.list.PageListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.ArticleService;
import fremad.service.MatchService;
import fremad.service.TeamService;

@Component
@Scope("request")
public class ArticleProcessor {
	private static final Logger LOG = LoggerFactory
			.getLogger(ArticleProcessor.class);

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	SessionSecurityContext securityContext;
	
	//----------------------ARTICLE METHODS----------------------

	public ArticleListObject getArticles(String articleType) {
		return articleService.getArticles(articleType);
	}

	public ArticleListObject getArticleHeaders() {
		ArticleListObject articleHeaders = articleService.getArticles("MATCH");
		articleHeaders = decorateMatchHeaders(articleHeaders);
		articleHeaders.addAll(articleService.getArticles("NEWS"));
		removeContent(articleHeaders);
		sortArticles(articleHeaders);
		return articleHeaders;
	}
	
	public ArticleListObject getArticleHeadersShort() {
		int maxSize = 5;
		ArticleListObject articleList = getArticleHeaders();
		if(maxSize > articleList.getList().size()){
			maxSize = articleList.getList().size();
		}
		ArticleListObject shortList = new ArticleListObject();
		shortList.addAll(articleList.getList().subList(0, maxSize));
		return shortList;
	}

	public ArticleObject getArticle(int id) {
		return articleService.getArticle(id);
	}

	public ArticleObject addArticle(ArticleObject article) {
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		article.setAuthorId(securityContext.getUserId());
		return articleService.addArticle(article);
	}

	public ArticleObject updateArticle(ArticleObject article) {
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return articleService.updateArticle(article);
	}

	public ArticleObject deleteArticle(ArticleObject article) {
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return articleService.deleteArticle(article);
	}
	
	public ArticleObject getArticleFromUrl(String url){
		return articleService.getArticle(articleService.getPage(url).getArticleId());
	}
	

	private ArticleListObject decorateMatchHeaders(ArticleListObject articleHeaders) {
		ArticleListObject filteredArticleList = new ArticleListObject();
		for(ArticleObject article : articleHeaders){
			MatchReportObject report = null;
			MatchReportListObject reports = articleService.getMatchReports();
			for(MatchReportObject currentReport : reports){
				if(article.getId() == currentReport.getArticleId()){
					report = currentReport;
				}
			}
			if(report == null){
				LOG.debug("Missmatch");
			}else{
				article.setContext(decorateContext(article.getContext(), report));
				filteredArticleList.add(article);
			}
		}
		return filteredArticleList;
	}
	
	private String decorateContext(String context, MatchReportObject report) {
		LOG.debug("dud0");
		LOG.debug("matchService.getMatch(report.getMatchId(" + report.getMatchId() + "))");
		MatchObject match = matchService.getMatch(report.getMatchId());
		LOG.debug("dud1");
		String fremadName = teamService.getTeam(match.getFremadTeam()).getName();
		LOG.debug("dud2");
		String homeTeam = match.isHomeMatch() ? fremadName : match.getOpposingTeamName();
		LOG.debug("dud3");
		String awayTeam = match.isHomeMatch() ? match.getOpposingTeamName() : fremadName;
		LOG.debug("dud4");
		context = homeTeam.toUpperCase() + " - " + awayTeam.toUpperCase() + " " 
				+ report.getHomeScore() + "-" + report.getAwayScore() + " ("
				+ report.getHomeScorePause() + "-" + report.getAwayScorePause() + "): "
				+ context;
		LOG.debug("dud5");
		return context;
	}

	private void removeContent(ArticleListObject articleHeaders) {
		for (ArticleObject article : articleHeaders){
			article.setContent("");
		}
		
	}
	
	private void sortArticles(ArticleListObject articleHeaders) {
		if (articleHeaders.size() > 0){
			Collections.sort(articleHeaders.getList(), new Comparator<ArticleObject>(){
				@Override
				public int compare(final ArticleObject obj2, final ArticleObject obj1){
					return obj1.getDate().compareTo(obj2.getDate());
				}
			});
		}
		
	}
	
	//----------------------PAGE METHODS----------------------	
	
	public PageListObject getPages(){
		return articleService.getPages();
	}
	
	public PageListObject getPublishedPages() {
		return articleService.getPublishedPages();
	}
	public PageObject addPage(PageObject page){
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return articleService.addPage(page);
	}

	public  PageObject updatePage(PageObject page){
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return articleService.updatePage(page);
	}

	//----------------------MATCH REPORT METHODS----------------------
	
	public MatchReportListObject getMatchReports(){
		return articleService.getMatchReports();
	}
	public MatchReportObject getMatchReport(int articleId){
		return articleService.getMatchReport(articleId);
	}
	public MatchReportObject addMatchReport(MatchReportObject report){
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return articleService.addMatchReport(report);
	}
	public  MatchReportObject updateMatchReport(MatchReportObject report){
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		return articleService.updateMatchReport(report);
	}





}
