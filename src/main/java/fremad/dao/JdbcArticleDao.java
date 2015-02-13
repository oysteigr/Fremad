package fremad.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		ArticleListObject articles = new ArticleListObject();

/*		try {
			this.prpstm = this.conn.prepareStatement("SELECT * FROM "
					+ SqlTablesConstants.SQL_TABLE_NAME_ARTICLE
					+ " WHERE type = ?");
			this.prpstm.setString(1, articleType);
			ResultSet res = this.prpstm.executeQuery();
			while (res.next()) {
				articles.add(new ArticleObject(res.getInt("id"), res
						.getInt("author_id"), res.getTimestamp("date"), res
						.getString("type"), res.getString("header"), res
						.getString("context"), res.getString("content"), res
						.getString("image_url"), res.getBoolean("published")));
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
*/
		return articles;
	}

	@Override
	public ArticleObject addArticle(ArticleObject article) {
		String sql = "INSERT IGNORE INTO "
				+ SqlTablesConstants.SQL_TABLE_NAME_ARTICLE
				+ " "
				+ "(author_id, date, type, header, context, content, image_url, published) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		LOG.debug("In updateArticle with sql: " + sql);

/*		try {
			prpstm = conn
					.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			prpstm.setInt(1, article.getAuthorId());
			prpstm.setTimestamp(2, article.getDate());
			prpstm.setString(3, article.getArticleType());
			prpstm.setString(4, article.getHeader());
			prpstm.setString(5, article.getContext());
			prpstm.setString(6, article.getContent());
			prpstm.setString(7, article.getImageURL());
			prpstm.setBoolean(8, article.isPublished());
			prpstm.executeUpdate();

			ResultSet rs = prpstm.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}
		article.setId(key);
*/
		return article;
	}

	@Override
	public ArticleObject updateArticle(ArticleObject article) {
		String sql = "UPDATE " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE
				+ " SET " + " author_id = ?, " + " date = ?, " + " type = ?, "
				+ " header = ?, " + " context = ?, " + " content = ?, "
				+ " image_url = ?, " + " published = ? " + " WHERE id = ?";

		LOG.debug("In updateArticle with sql: " + sql);
/*		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, article.getAuthorId());
			prpstm.setTimestamp(2, article.getDate());
			prpstm.setString(3, article.getArticleType());
			prpstm.setString(4, article.getHeader());
			prpstm.setString(5, article.getContext());
			prpstm.setString(6, article.getContent());
			prpstm.setString(7, article.getImageURL());
			prpstm.setBoolean(8, article.isPublished());
			prpstm.setInt(9, article.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}*/
		return article;
	}

	@Override
	public ArticleObject deleteArticle(ArticleObject article) {
		String sql = "DELETE FROM " + SqlTablesConstants.SQL_TABLE_NAME_ARTICLE
				+ " WHERE " + " id = ?";

		LOG.debug("In deleteArticle with sql: " + sql);
/*		try {
			prpstm = conn.prepareStatement(sql);
			prpstm.setInt(1, article.getId());
			prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.toString());
			return null;
		}*/
		return article;
	}

}
