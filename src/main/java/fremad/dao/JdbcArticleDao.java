package fremad.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.ArticleObject;
import fremad.domain.MatchReportObject;
import fremad.domain.PageObject;
import fremad.domain.list.ArticleListObject;
import fremad.domain.list.MatchReportListObject;
import fremad.domain.list.PageListObject;

@Repository
public class JdbcArticleDao extends JdbcConnection implements ArticleDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(JdbcArticleDao.class);

	public JdbcArticleDao() {
		super();
	}
	
	//----------------------ARTICLE METHODS----------------------

	@Override
	public ArticleListObject getArticles(final String articleType) {
		LOG.debug("In getArticles(articleType)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE + " where article_type = ?";
		ArticleListObject articles = new ArticleListObject();
		
		articles.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new PreparedStatementSetter() {
				@Override
				public void setValues(java.sql.PreparedStatement ps)
						throws SQLException {
					ps.setString(1, articleType);
				}
			}, new BeanPropertyRowMapper<>(ArticleObject.class)));

		return articles;
	}
	
	@Override
	public ArticleObject getArticle(int id) {
		LOG.debug("In getArticle(parseInt)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE + " where id = :id";
		SqlParameterSource parameters = new MapSqlParameterSource("id", id);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new UserBeanPropertyRowMapper<>(ArticleObject.class));
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
	
	//----------------------PAGE METHODS----------------------
	
	@Override
	public PageListObject getPages(){
		LOG.debug("In getPages()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PAGE + " ORDER BY priority ASC;";
		PageListObject pages = new PageListObject();
		
		pages.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(PageObject.class)));
		return pages;
	}
	
	@Override
	public PageListObject getPublishedPages() {
		LOG.debug("In getPages()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PAGE + " WHERE published = 1 ORDER BY priority ASC;";
		PageListObject pages = new PageListObject();
		
		pages.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(PageObject.class)));
		return pages;
	}
	
	@Override
	public PageObject getPage(String urlName){
		LOG.debug("In getPage(urlName)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_PAGE + " where url_name = :urlName";
		SqlParameterSource parameters = new MapSqlParameterSource("urlName", urlName);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new UserBeanPropertyRowMapper<>(PageObject.class));
	}
	
	@Override
	public PageObject addPage(PageObject page) {
		LOG.debug("In addPage(page)");
		
		LOG.debug("In addPage with title: " + page.getArticleTitle());
		
		SimpleJdbcInsert insertPage = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_PAGE);
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(page);
		
		insertPage.execute(parameters);
		
		return page;
	}
	
	@Override
	public PageObject updatePage(PageObject page){
		LOG.debug("In updatePage(page)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_PAGE + " set "
				+ "article_title = :articleTitle, "
				+ "url_name = :urlName, "
				+ "priority = :priority, "
				+ "published = :published "
				+ "where article_id = :articleId";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(page);
	
		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return page;
	}
	
	//----------------------MATCH REPORT METHODS----------------------
	
	@Override
	public MatchReportListObject getMatchReports(){
		LOG.debug("In getMatchReports()");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_MATCH_REPORT;
		MatchReportListObject reports = new MatchReportListObject();
		
		reports.addAll(this.namedParameterJdbcTemplate.getJdbcOperations().query(query, new BeanPropertyRowMapper<>(MatchReportObject.class)));
		return reports;
	}
	
	@Override
	public MatchReportObject getMatchReport(int articleId){
		LOG.debug("In getMatchReport(articleId)");
		
		String query = "select * from " + SqlTablesConstants.SQL_TABLE_NAME_MATCH_REPORT + " where article_id = :articleId";
		SqlParameterSource parameters = new MapSqlParameterSource("articleId", articleId);
		
		return namedParameterJdbcTemplate.queryForObject(query, parameters, new UserBeanPropertyRowMapper<>(MatchReportObject.class));
	}
	
	@Override
	public MatchReportObject addMatchReport(MatchReportObject report) {
		LOG.debug("In addPage(page)");
		
		SimpleJdbcInsert insertPage = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_MATCH_REPORT);
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(report);
		
		insertPage.execute(parameters);
		
		return report;
	}
	
	@Override
	public MatchReportObject updateMatchReport(MatchReportObject report){
		LOG.debug("In updatePage(page)");
		
		String updateStatement = "update " + SqlTablesConstants.SQL_TABLE_NAME_MATCH_REPORT + " set "
				+ "match_id = :matchId, "
				+ "home_score = :homeScore, "
				+ "away_score = :awayScore, "
				+ "home_score_pause = :homeScorePause, "
				+ "away_score_pause = :awayScorePause, "
				+ "supporters = :supporters, "
				+ "published = :published "
				+ "where article_id = :articleId";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(report);
	
		this.namedParameterJdbcTemplate.update(updateStatement, parameters);
		
		return report;
	}

}
