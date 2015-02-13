package fremad.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcDbSetupDao extends JdbcConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcDbSetupDao.class);
	
	public JdbcDbSetupDao() {
		super();
	}
	
	public void create() {
		
		for (String sql : SqlTablesConstants.SQL_CREATE_TABLE_STRINGS) {
			LOG.debug(sql);
			namedParameterJdbcTemplate.getJdbcOperations().execute(sql);
		}
	}
	
	public void delete() {
	
		for (String sql : SqlTablesConstants.SQL_DROP_TABLE_STRINGS) {
			namedParameterJdbcTemplate.getJdbcOperations().execute(sql);
		}
	
	}

	public boolean isCreated() {
		return true;
/*		connect();
		
		res = select("show tables");
		try {
			int count = 0;
			while (res.next()) {
				count++;
			}
			if (count != SqlTablesConstants.SQL_CREATE_TABLE_STRINGS.length) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			LOG.error(e.toString());
			return false;
		} finally {
	closeAll();
		}*/
	}
}
