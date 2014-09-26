package fremad.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.ArticleObject;
import fremad.service.ArticleService;


@Component
@Scope("request")
public class ArticleProcessor {
	
	@Autowired
	private ArticleService articleService;

	
	public ArticleObject getArticle(int number){
		
		return articleService.getArticle(number);
	}
}
