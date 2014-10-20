package fremad.service;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
			LOG.info("It is also working working!!");
			
		}
		
	}
	
}
