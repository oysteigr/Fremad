package fremad.dao;


import fremad.domain.ArticleObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.PageListObject;


public interface ArticleDao{
	
	ArticleListObject getArticles(String articleType);
	ArticleObject getArticle(int parseInt);
	ArticleObject addArticle(ArticleObject article);
	ArticleObject updateArticle(ArticleObject article);
	ArticleObject deleteArticle(ArticleObject article);
	
	PageListObject getPages();
	PageListObject getPublishedPages();
	PageObject getPage(String urlName);
	PageObject addPage(PageObject page);
	PageObject updatePage(PageObject page);
}
