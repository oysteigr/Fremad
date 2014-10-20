package fremad.dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcConnection {

	Connection conn;
	PreparedStatement prpstm;
	
	public JdbcConnection() {
		try {
			Class.forName(SqlTablesConstants.JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(SqlTablesConstants.DB_URL, SqlTablesConstants.USER, SqlTablesConstants.PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet select(String sql) {
		try {
			prpstm = conn.prepareStatement(sql);
			return prpstm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int update(String sql) {
		try {
			prpstm = conn.prepareStatement(sql);
			return prpstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
