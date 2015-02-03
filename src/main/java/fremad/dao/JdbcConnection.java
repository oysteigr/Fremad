package fremad.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.proxy.PreparedStatementProxy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import fremad.dao.SqlTablesConstants;

@Repository
public class JdbcConnection {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);
	Connection conn = null;
	PreparedStatement prpstm = null;
	PreparedStatementProxy proxy = null;
	HikariGFXDPool pool;  
	ResultSet res;
	
	public JdbcConnection() {
		try {
			Class.forName(SqlTablesConstants.JDBC_DRIVER);
			pool = HikariGFXDPool.getInstance();
		} catch (ClassNotFoundException e) {
			LOG.error(e.toString());
		}
		

	}
	
	public void connect(){
		try {
			conn = pool.getConnection();  
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
	
	public void closeAll() {
		LOG.info("Closing statement :D: " + prpstm.toString());
		try {

			if(prpstm != null){
				prpstm.close();
			}
			if(res != null){
				res.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			LOG.error("Connection could not close");
		}
	}
	public void closeAll(String func) {
		LOG.info("Closing statement after: " + func);
		try {

			if(prpstm != null){
				prpstm.close();
			}
			if(res != null){
				res.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			LOG.error("Connection could not close");
		}
	}
}
