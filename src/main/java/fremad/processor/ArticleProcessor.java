package fremad.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.ArticleObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.PageListObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.ArticleService;

@Component
@Scope("request")
public class ArticleProcessor {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	SessionSecurityContext securityContext;

	public ArticleListObject getArticles(String articleType) {
		return articleService.getArticles(articleType);
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




}
