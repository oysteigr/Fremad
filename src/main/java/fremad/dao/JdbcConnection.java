package fremad.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcConnection extends JdbcDaoSupport{

	private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
 
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.getDataSource());
	}
	
	public JdbcConnection() {

	}
	
/*	public void connect(){
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
	}*/
}
