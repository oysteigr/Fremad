package fremad.processor;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import fremad.domain.MatchObject;
import fremad.domain.list.MatchListObject;
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
					return obj1.getDate().compareTo(obj2.getDate());
				}
			});
		}
		return response;
	}
	
	public MatchListObject getThisYearsMatches(){
		MatchListObject response = matchService.getThisYearsMatches();
		response = getPlayedMatches(response);
		if(response.getList().size() < 10){
			response = new MatchListObject();
			response.addAll(getPlayedMatches(matchService.getMatches()).getList().subList(0, 11));
		}
		if (response.size() > 0){
			Collections.sort(response.getList(), new Comparator<MatchObject>(){
				@Override
				public int compare(final MatchObject obj2, final MatchObject obj1){
					return obj1.getDate().compareTo(obj2.getDate());
				}
			});
		}
		return response;
	}
	

	private MatchListObject getPlayedMatches(MatchListObject response) {
		MatchListObject result = new MatchListObject();
		for (MatchObject match : response){
			if(match.getDate().before(new Timestamp(new Date().getTime()))){
				result.add(match);
			}
		}
		return result;
		
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
		for (MatchObject matchObjcet : Lists.reverse(matchListObject.getList())){
			if(matchObjcet.getDate().getTime() < dateNow.getTime()){
				return matchObjcet;
			}
		}
		return null;
	}
}