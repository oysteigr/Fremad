package fremad.processor;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.collect.Lists;

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
	
	public MatchListObject getMatches(int teamId){
		MatchListObject response = matchService.getMatches(teamId);
		if (response.size() > 0){
			Collections.sort(response.getList(), new Comparator<MatchObject>(){
				@Override
				public int compare(final MatchObject obj1, final MatchObject obj2){
					return obj2.getDate().compareTo(obj1.getDate());
				}
			});
		}
		return response;
	}
	
	public MatchObject getNextMatch(int teamId){
		MatchListObject matchListObject = getMatches(teamId);
		Date dateNow = new Date();
		for (MatchObject matchObjcet : matchListObject){
			if(matchObjcet.getDate().getTime() > dateNow.getTime()){
				return matchObjcet;
			}
		}
		return null;
	}
	
	public MatchObject getPrevMatch(int teamId){
		MatchListObject matchListObject = getMatches(teamId);
		Lists.reverse(matchListObject.getList());
		Date dateNow = new Date();
		for (MatchObject matchObjcet : matchListObject){
			if(matchObjcet.getDate().getTime() < dateNow.getTime()){
				return matchObjcet;
			}
		}
		return null;
	}
}