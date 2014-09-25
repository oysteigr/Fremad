package fremad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import fremad.dao.SqlTablesConstants;
import fremad.domain.*;




public class TestDao implements SqlTablesConstants{
	


	public String insertArticle(ArticleObject article) throws Exception {
		
		fremad.dao.SqlUtils.createTableIfItDoesNotExist(SQL_TABLE_NAME_ARTICLE, SQL_TABLE_VARIABLES_ARTICLE);
		
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		
		try {
			
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			sql = "INSERT INTO articles ("
					+ "AuthorId, "
					+ "ArticleDate, "
					+ "ArticleType,"
					+ "ArticleHeader,"
					+ "ArticleContext,"
					+ "ArticleContent,"
					+ "ArticleImageUrl) "
					+ "VALUES ("
					+ "'1', "
					+ "NOW(), "
					+ "'NEWS',"
					+ "'This is news', "
					+ "'This is something in context', "
					+ "'This is a long content', "
					+ "'images/feiring.jpg'); ";
			
			stmt.executeUpdate(sql);

		} catch(SQLException se) {
			return "" + se.getErrorCode();
		} catch(Exception e) {
			return "Exception";
		} finally {
			try {
				if(stmt != null)
					conn.close();
			} catch(SQLException se) {
				return "SQLException added";
			}
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException se) {
				return "SQLException added";
			}
		}
		return "Dummy data added";
	}

	
}
