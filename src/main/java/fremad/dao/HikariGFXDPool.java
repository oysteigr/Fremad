package fremad.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import fremad.dao.SqlTablesConstants;

public class HikariGFXDPool
{

    private static HikariGFXDPool instance = null;
    private HikariDataSource ds = null;

    static
    {
        try
        {
            instance = new HikariGFXDPool();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    private HikariGFXDPool()
    {
        HikariConfig config = new HikariConfig();

/*        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        config.setJdbcUrl(SqlTablesConstants.DB_URL);
        config.setUsername(SqlTablesConstants.USER);
        config.setPassword( SqlTablesConstants.PASS);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        
*/
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(250);
        config.setInitializationFailFast(true);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "3306");
        config.addDataSourceProperty("databaseName", "fremad");
        config.addDataSourceProperty("user", SqlTablesConstants.USER);
        config.addDataSourceProperty("password", SqlTablesConstants.PASS);
        config.addDataSourceProperty("cachePrepStmts", "false");

        ds = new HikariDataSource(config);
    }

    public static HikariGFXDPool getInstance ()
    {
        return instance;
    }

    public Connection getConnection()  throws SQLException
    {
        return ds.getConnection();
    }

}
