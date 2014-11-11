package fremad.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcConnection {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);
	Connection conn;
	PreparedStatement prpstm;
	
	public JdbcConnection() {
		try {
			Class.forName(SqlTablesConstants.JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			LOG.error(e.toString());
		}
		
		try {
			conn = DriverManager.getConnection(SqlTablesConstants.DB_URL, SqlTablesConstants.USER, SqlTablesConstants.PASS);
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
	}
	
	public ResultSet select(String sql) {
		try {
			prpstm = conn.prepareStatement(sql);
			return prpstm.executeQuery();
		} catch (SQLException e) {
			LOG.error("Select failed: "+ e.toString());
			return null;
		}
	}
	
	public int update(String sql) {
		try {
			prpstm = conn.prepareStatement(sql);
			return prpstm.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Update failed: " + e.toString());
			return -1;
		}
	}
}
