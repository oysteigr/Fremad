package fremad.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.ArticleObject;
import fremad.domain.list.ArticleListObject;

@Repository
public class JdbcArticleDao extends JdbcConnection implements ArticleDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(JdbcArticleDao.class);

	public JdbcArticleDao() {
		super();
	}

	@Override
	public ArticleListObject getArticles(String articleType) {
		LOG.debug("In getArticles(articleType)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE;
		ArticleListObject articles = new ArticleListObject();
		
		articles.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(ArticleObject.class)));

		return articles;
	}

	@Override
	public ArticleObject addArticle(ArticleObject article) {
		LOG.debug("In addArticle(article)");
		
		SimpleJdbcInsert insertArticle = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_ARTICLE)
			.usingGeneratedKeyColumns("id");
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(article);
		
		Number newId = insertArticle.executeAndReturnKey(parameters);
		
		if(newId != null){
			article.setId(newId.intValue());
			return article;
		}
		
		return article;
	}

	@Override
	public ArticleObject updateArticle(ArticleObject article) {
		LOG.debug("In updateArticle(article)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE + " set "
				+ "author_id = :authorId, "
				+ "date = :date, "
				+ "article_type = :articleType, "
				+ "header = :header, "
				+ "context = :context, "
				+ "content = :content, "
				+ "image_url = :imageUrl, "
				+ "published = :published "
				+ "where id = :id";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(article);

		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return article;
	}

	@Override
	public ArticleObject deleteArticle(ArticleObject article) {
		LOG.debug("In deleteArticle(article)");
		
		String query = "delete from " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE + " where id = :id";
		SqlParameterSource parameters = new MapSqlParameterSource("id", article.getId());
		
		namedParameterJdbcTemplate.update(query, parameters);
		
		return article;
	}

}
