package fremad.service;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.ArticleDao;
import fremad.domain.ArticleObject;

@Service
@Scope("singleton")
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	public ArticleObject getArticle(int number){
		
		return articleDao.getArticle(number);
		
		

	}
}
