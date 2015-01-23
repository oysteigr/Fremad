package fremad.dao;


import fremad.domain.ArticleObject;
import fremad.domain.list.ArticleListObject;


public interface ArticleDao{
	
	public ArticleListObject getArticles(String articleType);
	public ArticleObject addArticle(ArticleObject article);
	public ArticleObject updateArticle(ArticleObject article);
	public ArticleObject deleteArticle(ArticleObject article);
}
