package fremad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import fremad.tools.ConfigProperties;

public class SqlUtils implements SqlTablesConstants{
	
	final static String USERNAME = "MySQL_userName" ;
	final static String PASSWORD = "MySQL_password";
	final static String DRIVER = "MySQL_driver";
	final static String URL = "MySQL_url";
	
	@Autowired
	ConfigProperties configProperties;
	
	//TODO: FIX THIS CLASS
	
	static public void createTableIfItDoesNotExist(String table, String rows) throws Exception{
		

		ConfigProperties configProperties = new ConfigProperties();
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			
			Class.forName(configProperties.getPropValues(DRIVER));
			
			conn = DriverManager.getConnection(
					configProperties.getPropValues(URL), 
					configProperties.getPropValues(USERNAME), 
					configProperties.getPropValues(PASSWORD));
			
			stmt = conn.createStatement();

			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + table + " (" + rows + ")");
		} catch(SQLException se) {
			throw new SQLException("error: in createTableIfItDoesNotExist");
		} catch(Exception e) {
			throw new SQLException("error: in createTableIfItDoesNotExist");
		} finally {
			try {
				if(stmt != null)
					conn.close();
			} catch(SQLException se) {
				throw new SQLException("error: in createTableIfItDoesNotExist");
			}
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException se) {
				throw new SQLException("error: in createTableIfItDoesNotExist");
			}
		}
	}
}
