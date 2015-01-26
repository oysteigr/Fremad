package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.ArticleObject;
import fremad.domain.list.ArticleListObject;
import fremad.service.ArticleService;

@Component
@Scope("request")
public class ArticleProcessor {

	@Autowired
	private ArticleService articleService;

	public ArticleListObject getArticles(String articleType) {
		return articleService.getArticles(articleType);
	}

	public ArticleObject addArticle(ArticleObject article) {
		return articleService.addArticle(article);
	}

	public ArticleObject updateArticle(ArticleObject article) {
		return articleService.updateArticle(article);
	}

	public ArticleObject deleteArticle(ArticleObject article) {
		return articleService.deleteArticle(article);
	}
}
