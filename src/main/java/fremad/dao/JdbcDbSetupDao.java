package fremad.dao;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fremad.dao.SqlTablesConstants;

public class JdbcDbSetupDao extends JdbcConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcDbSetupDao.class);
	
	public JdbcLeagueDao() {
		super();
	}
	
	public void create() {
		try {
            for (String sql : SqlTablesConstants.SQL_CREATE_TABLE_STRINGS) {
                prpstm = conn.prepareStatement(sql);
                prpstm.executeUpdate();
            }
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
	}

    public void delete() {
		try {
            for (String sql : SqlTablesConstants.SQL_DROP_TABLE_STRINGS) {
                prpstm = conn.prepareStatement(sql);
                prpstm.executeUpdate();
            }
		} catch (SQLException e) {
			LOG.error(e.toString());
		}
    }

    public boolean isCreated() {
        ResultSet res = select("show tables");
        try {
            int count = 0;
            while (res.next()) {
                count++;
            }   
            if (count != length(SqlTablesConstants.SQL_CREATE_TABLE_STRINGS)) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e.toString());
        }
    }
}
