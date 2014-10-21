package fremad.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fremad.dao.SqlTablesConstants;

public class JdbcDbSetupDao extends JdbcConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcDbSetupDao.class);
	
	public JdbcDbSetupDao() {
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
            if (count != SqlTablesConstants.SQL_CREATE_TABLE_STRINGS.length) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e.toString());
            return false;
        }
    }
}
