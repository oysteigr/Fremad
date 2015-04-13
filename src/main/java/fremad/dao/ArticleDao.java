package fremad.dao;


import fremad.domain.ArticleObject;
import fremad.domain.MatchReportObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.MatchReportListObject;
import fremad.domain.list.PageListObject;


public interface ArticleDao{
	
	//----------------------ARTICLE METHODS----------------------
	
	ArticleListObject getArticles(String articleType);
	ArticleObject getArticle(int parseInt);
	ArticleObject addArticle(ArticleObject article);
	ArticleObject updateArticle(ArticleObject article);
	ArticleObject deleteArticle(ArticleObject article);
	
	//----------------------PAGE METHODS----------------------
	
	PageListObject getPages();
	PageListObject getPublishedPages();
	PageObject getPage(String urlName);
	PageObject addPage(PageObject page);
	PageObject updatePage(PageObject page);
	
	//----------------------MATCH REPORT METHODS----------------------
	
	MatchReportListObject getMatchReports();
	MatchReportObject getMatchReport(int articleId);
	MatchReportObject addMatchReport(MatchReportObject report);
	MatchReportObject updateMatchReport(MatchReportObject report);
}
