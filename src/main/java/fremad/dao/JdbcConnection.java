package fremad.dao;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import fremad.tools.ConfigProperties;

@Repository
public class JdbcConnection extends JdbcDaoSupport {

	private static final Logger LOG = LoggerFactory.getLogger(JdbcConnection.class);
	
	final static String USERNAME = "MySQL_userName" ;
	final static String PASSWORD = "MySQL_password";
	final static String DRIVER = "MySQL_driver";
	final static String URL = "MySQL_url";
	
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	ConfigProperties configProperties;

	@PostConstruct
	private void initialize() {
		setDataSource(createDataSource());
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.getDataSource());
		LOG.debug("initialize NamedParameterJdbcTemplate");
	}


	private DriverManagerDataSource createDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(configProperties.getPropValues(DRIVER));

		dataSource.setUrl(configProperties.getPropValues(URL));

		dataSource.setUsername(configProperties.getPropValues(USERNAME));

		dataSource.setPassword(configProperties.getPropValues(PASSWORD));

		return dataSource;
	}

	/*
	 * public void connect(){ try { conn = pool.getConnection(); } catch
	 * (SQLException e) { LOG.error(e.toString()); } }
	 * 
	 * public ResultSet select(String sql) { try { prpstm =
	 * conn.prepareStatement(sql); return prpstm.executeQuery(); } catch
	 * (SQLException e) { LOG.error("Select failed: "+ e.toString()); return
	 * null; } }
	 * 
	 * public int update(String sql) { try { prpstm =
	 * conn.prepareStatement(sql); return prpstm.executeUpdate(); } catch
	 * (SQLException e) { LOG.error("Update failed: " + e.toString()); return
	 * -1; } }
	 */
}
