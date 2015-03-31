package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.ArticleDao;
import fremad.domain.ArticleObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.PageListObject;

@Service
@Scope("singleton")
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	public ArticleListObject getArticles(String articleType) {
		return articleDao.getArticles(articleType);
	}
	public ArticleObject getArticle(int id) {
		return articleDao.getArticle(id);
	}
	public ArticleObject addArticle(ArticleObject article) {
		return articleDao.addArticle(article);
	}
	public ArticleObject updateArticle(ArticleObject article) {
		return articleDao.updateArticle(article);
	}
	public ArticleObject deleteArticle(ArticleObject article) {
		return articleDao.deleteArticle(article);
	}
	public PageListObject getPages(){
		return articleDao.getPages();
	}
	public PageListObject getPublishedPages() {
		return articleDao.getPublishedPages();
	}
	public PageObject getPage(String urlName){
		return articleDao.getPage(urlName);
	}
	public PageObject addPage(PageObject page){
		return articleDao.addPage(page);
	}
	public  PageObject updatePage(PageObject page){
		return articleDao.updatePage(page);
	}

}
