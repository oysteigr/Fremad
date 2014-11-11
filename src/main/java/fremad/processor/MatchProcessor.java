package fremad.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.MatchObject;
import fremad.service.MatchService;



@Component
@Scope("request")
public class MatchProcessor {
	
	@Autowired
	MatchService matchService;
	
	public MatchObject getMatch(int matchId){
		return matchService.getMatch(matchId);
		
	}
	public List<MatchObject> getMatches(int leagueId){
		return matchService.getMatches(leagueId);
	}
}