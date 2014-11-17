package fremad.processor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.MatchListObject;
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
	
	public MatchListObject getMatches(int leagueId){
		MatchListObject response = matchService.getMatches(leagueId);
		if (response.size() > 0){
			Collections.sort(response.getList(), new Comparator<MatchObject>(){
				@Override
				public int compare(final MatchObject obj1, final MatchObject obj2){
					return obj1.getDate().compareTo(obj2.getDate());
				}
			});
		}
		return response;
	}
}