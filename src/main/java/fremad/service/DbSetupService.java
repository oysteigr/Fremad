package fremad.service;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcDbSetupDao;

@Service
@Scope("singleton")
public class DbSetupService {
	private static final Logger LOG = LoggerFactory.getLogger(DbSetupService.class);

	public DbSetupService() {
        JdbcDbSetupDao dao = new JdbcDbSetupDao();
        if (! dao.isCreated()) {
            LOG.debug("Setting up database");
            dao.create();
        }
	}
}
