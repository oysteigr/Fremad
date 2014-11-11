package fremad.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import fremad.dao.SqlTablesConstants;
import fremad.domain.*;


@Repository
public class ArticleDao implements SqlTablesConstants{
	


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

	public ArticleObject getArticle(int number) {
		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		ResultSet response = null;
		List<ArticleObject> articleList = new ArrayList<ArticleObject>();
		try {
			
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			
			sql = "SELECT * FROM articles" ;
			
			response = stmt.executeQuery(sql);

		} catch(SQLException se) {
			return null;
		} catch(Exception e) {
			return null;
		} finally {

			try{
				while(response.next()){
					ArticleObject articleObject = new ArticleObject();
					articleObject.setId(Integer.parseInt(response.getString("ArticleId")));
					articleObject.setAuthorId(Integer.parseInt(response.getString("AuthorId")));
					articleObject.setDate(response.getTimestamp("ArticleDate"));
					articleObject.setArticleType(response.getString("ArticleType"));
					articleObject.setHeader(response.getString("ArticleHeader"));
					articleObject.setContext(response.getString("ArticleContext"));
					articleObject.setContent(response.getString("ArticleContent"));
					articleObject.setImageURL(response.getString("ArticleImageUrl"));
					articleList.add(articleObject);
				}
			} catch (Exception e) { 
				System.err.println("Got an exception in articleList! "); 
				System.err.println(e.getMessage());
				System.err.println(articleList.toString()); 
			}
			try {
				if(stmt != null)
					conn.close();
			} catch(SQLException se) {
				return null;
			}
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException se) {
				return null;
			}
		}
		System.err.println(response.toString()); 

		return articleList.get(number);
	}

	
}
