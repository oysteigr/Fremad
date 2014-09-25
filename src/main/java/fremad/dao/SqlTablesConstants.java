package fremad.dao;

public interface SqlTablesConstants {
	
	/**
	 * This is references to tables and their variables
	 * 
	 */
	
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = "jdbc:mysql://localhost:3306/fremad";

	// Database credentials
	String USER = "famagusta";
	String PASS = "fremad123";
	
	// Table names
	
	String SQL_TABLE_NAME_ARTICLE = "articles";
	
	
	// Table values
	
	String SQL_TABLE_VARIABLES_ARTICLE = ""
		    + "ArticleId INT(64) NOT NULL AUTO_INCREMENT, "
		    + "AuthorId INT(64), "
		    + "ArticleDate DATE, "
		    + "ArticleType VARCHAR(32), "
		    + "ArticleHeader VARCHAR(64), "
		    + "ArticleContext VARCHAR(256), "
		    + "ArticleContent TEXT, "
		    + "ArticleImageUrl VARCHAR(64), "
		    + "PRIMARY KEY ( ArticleId )";
}

