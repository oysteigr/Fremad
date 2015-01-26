package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.ArticleDao;
import fremad.domain.ArticleObject;
import fremad.domain.list.ArticleListObject;

@Service
@Scope("singleton")
public class ArticleService {

	@Autowired
	private ArticleDao jdbcArticleDao;

	public ArticleListObject getArticles(String articleType) {
		return jdbcArticleDao.getArticles(articleType);
	}

	public ArticleObject addArticle(ArticleObject article) {
		return jdbcArticleDao.addArticle(article);
	}

	public ArticleObject updateArticle(ArticleObject article) {
		return jdbcArticleDao.updateArticle(article);
	}

	public ArticleObject deleteArticle(ArticleObject article) {
		return jdbcArticleDao.deleteArticle(article);
	}
}
