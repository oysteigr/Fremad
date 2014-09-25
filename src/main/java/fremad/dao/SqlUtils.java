package fremad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils implements SqlTablesConstants{
	static public void createTableIfItDoesNotExist(String table, String rows) throws Exception{
		Connection conn = null;
		Statement stmt = null;

		
		try {
			
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
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
