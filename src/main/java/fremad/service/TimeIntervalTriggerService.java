package fremad.service;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcLeagueDao;
import fremad.dao.JdbcMatchDao;
import fremad.dao.JdbcTeamDao;
import fremad.dao.LeagueDao;
import fremad.dao.MatchDao;
import fremad.dao.TeamDao;
import fremad.domain.LeagueListObject;
import fremad.domain.LeagueObject;
import fremad.domain.MatchListObject;
import fremad.domain.MatchObject;
import fremad.domain.TeamListObject;
import fremad.domain.TeamObject;
import fremad.processor.LeagueProcessor;
import fremad.processor.MatchProcessor;
import fremad.processor.TeamProcessor;
import fremad.utils.UrlParser;

@Service
@Scope("singleton")
public class TimeIntervalTriggerService {
	private static final Logger LOG = LoggerFactory.getLogger(TimeIntervalTriggerService.class);
	private static final int updateFrequency = 1000 * 60 * 60 * 3;

	
	public TimeIntervalTriggerService() {
		super();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new UpdateTable(),0,updateFrequency);
		timer.scheduleAtFixedRate(new UpdateFixture(),0,updateFrequency);
	}
	
	class UpdateTable extends TimerTask{

		@Override
		public void run() {
			LOG.info("It is working!!");
			
		}
		
	}
	
	class UpdateFixture extends TimerTask{

		@Override
		public void run() {
			LOG.info("QQQ Called run()");
			
			TeamDao teamDao = new JdbcTeamDao();
			MatchDao matchDao = new JdbcMatchDao();
			LeagueDao leagueDao = new JdbcLeagueDao();
			
			LeagueListObject leagueList = leagueDao.getLeagues();
			
			LOG.debug("Found " + leagueList.size() + " leagues");
			
			int matchesAdded = 0;
			for (LeagueObject league : leagueList.getList()) {
				TeamObject team = teamDao.getTeam(league.getTeam());
				MatchListObject matches = UrlParser.getMatchListObject(team);
				LOG.debug("Found " + matches.size() + " matches for team " + league.getTeam());
				for (MatchObject match : matches) {
					if (matchDao.addMatch(match)) {
						matchesAdded++;
					} else {
						LOG.error("Failed to add match with id " + match.getId());
					}
				}
			}
			LOG.debug("Added " + matchesAdded + " matches");
		}
		
	}
	
}
