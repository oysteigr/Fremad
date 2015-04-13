package fremad.utils;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fremad.domain.LeagueObject;
import fremad.domain.MatchObject;
import fremad.domain.TableEntryObject;
import fremad.domain.TeamObject;
import fremad.domain.list.MatchListObject;
import fremad.domain.list.TableEntryListObject;

public final class UrlParser  extends UrlConstants{
	private static final Logger LOG = LoggerFactory.getLogger(UrlParser.class);
	
	public static TableEntryListObject getTableEntryListObject(LeagueObject leagueObject, boolean old){
		LOG.info("In getTableEntryListObject");
		
		TableEntryListObject tableEntryListObject = new TableEntryListObject();
		
		tableEntryListObject.setLeagueObject(leagueObject);
		
		Elements rows = getRowsFromTable(leagueObject.getId());
		
		for (int i = 2; i < rows.size(); i++) { 
			tableEntryListObject.add(getTableEntryFromRow(rows.get(i), leagueObject.getId(), old));
		}
		
		return tableEntryListObject;
	}
	
	public static MatchListObject getMatchListObject(TeamObject teamObject, LeagueObject leagueObject, boolean old){
		LOG.info("In getMatchListObject");
		
		MatchListObject matchListObject = new MatchListObject();
		
		Elements rows = getRowsFromFixture(leagueObject.getId());
		
		for (int i = 1; i < rows.size(); i++) { 
			MatchObject tempMatch = getMatchFromRow(rows.get(i), teamObject, leagueObject.getId(), old);
			if(tempMatch != null){
				matchListObject.add(tempMatch);
			}
		}
		
		return matchListObject;
	}
	
	public static String getLeagueNameFromId(int id){
		try {
			String urlString = "http://www.fotball.no/System-pages/Tabell/?tournamentId=" + id;
			URL url = new URL(urlString);
			Document doc = Jsoup.parse(url, 1000000);
			
			Element header = doc.select("h1").get(0); //select the first h2 header.
			
			return header.text();
			
		} catch (Exception e) {
			LOG.error(e.toString());
			return "ID_ERROR";
		}
		
	}
	
	public static String getTeamNameFromId(int id){
		
		try {
			String urlString = "http://www.fotball.no/Community/Lag/Hjem/?fiksId=" + id;
			URL url = new URL(urlString);
			Document doc1 = Jsoup.parse(url, 1000000);
			
			Element header = doc1.select("h1").get(0); //select the h1 header.
			
			if(header.text().equals("Feil på siden")){
				throw new Exception("No such team");
			}
			
			return header.text();
	
		} catch (Exception e) {
			LOG.error(e.toString());
			return "ID_ERROR";
		}
		
	}
	
	private static TableEntryObject getTableEntryFromRow(Element row, int leagueId, boolean old){
		
		TableEntryObject tableEntryObject = new TableEntryObject();
		
		Elements cols = row.select("td");
		
		tableEntryObject.setLeagueId(leagueId);
		tableEntryObject.setPos(Integer.parseInt(cols.get(URL_TABLE_POS).text()));	
		if(old){
			tableEntryObject.setTeamId(-1);
		}else{
			tableEntryObject.setTeamId(getTeamIdFromUrl(cols.get(URL_TABLE_TEAM)));
		}
		tableEntryObject.setTeamName(cols.get(URL_TABLE_TEAM).text());
		tableEntryObject.setMatchCount(Integer.parseInt(cols.get(URL_TABLE_MATCHES).text()));
		tableEntryObject.setGamesWon(Integer.parseInt(cols.get(URL_TABLE_WIN).text()));
		tableEntryObject.setGamesTied(Integer.parseInt(cols.get(URL_TABLE_TIED).text()));
		tableEntryObject.setGamesLost(Integer.parseInt(cols.get(URL_TABLE_LOST).text()));
		tableEntryObject.setGoalsScored(Integer.parseInt(cols.get(URL_TABLE_GOALS).text().split("-")[0].trim()));
		tableEntryObject.setGoalsConceded(Integer.parseInt(cols.get(URL_TABLE_GOALS).text().split("-")[1].trim()));
		tableEntryObject.setPoints(Integer.parseInt(cols.get(URL_TABLE_POINTS).text()));
		

		return tableEntryObject;
	}
		
	private static MatchObject getMatchFromRow(Element row, TeamObject teamObject, int leagueId, boolean old){
		
		MatchObject matchObject = new MatchObject();
		
		Elements cols = row.select("td");
				
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");	
		formatter.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
		
		try {
			Date date = formatter.parse(cols.get(URL_FIXTURES_DATE).text() + " " + cols.get(URL_FIXTURES_TIME).text());
			matchObject.setDate(new Timestamp(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(old){
			if(!teamObject.getName().equals(cols.get(URL_FIXTURES_HOMETEAM).text()) &&
					!teamObject.getName().equals(cols.get(URL_FIXTURES_AWAYTEAM).text())){
				return null;
			}
		}else{
			if((teamObject.getOnlineId() != getTeamIdFromUrl(cols.get(URL_FIXTURES_HOMETEAM))) &&
					(teamObject.getOnlineId() != getTeamIdFromUrl(cols.get(URL_FIXTURES_AWAYTEAM)))){
				return null;
			}
		}
		
		matchObject.setFremadTeam(teamObject.getId());
		if(old){
			matchObject.setHomeMatch(teamObject.getName().equals(cols.get(URL_FIXTURES_HOMETEAM).text()));
		}else{
			matchObject.setHomeMatch(teamObject.getOnlineId() == getTeamIdFromUrl(cols.get(URL_FIXTURES_HOMETEAM)));
		}
		matchObject.setField(cols.get(URL_FIXTURES_FIELD).text());
		matchObject.setLeague(leagueId);
		
		if(old){
			matchObject.setOpposingTeamId(-1);
			if(matchObject.isHomeMatch()){
				matchObject.setOpposingTeamName(cols.get(URL_FIXTURES_AWAYTEAM).text());
			}else{
				matchObject.setOpposingTeamName(cols.get(URL_FIXTURES_HOMETEAM).text());		
			}
		}else{
			if(matchObject.isHomeMatch()){
				matchObject.setOpposingTeamId(getTeamIdFromUrl(cols.get(URL_FIXTURES_AWAYTEAM)));
				matchObject.setOpposingTeamName(cols.get(URL_FIXTURES_AWAYTEAM).text());
			}else{
				matchObject.setOpposingTeamId(getTeamIdFromUrl(cols.get(URL_FIXTURES_HOMETEAM)));
				matchObject.setOpposingTeamName(cols.get(URL_FIXTURES_HOMETEAM).text());		
			}
		}
		LOG.debug("Before check result");
		if(cols.get(URL_FIXTURES_RESULT).text().equals("Ikke klart")){
			matchObject.setFremadGoals(-1);
			matchObject.setOpposingTeamGoals(-1);
			return matchObject;
		}
		if(cols.get(URL_FIXTURES_RESULT).text().trim().equals(":")){
			matchObject.setFremadGoals(-1);
			matchObject.setOpposingTeamGoals(-1);
			return matchObject;
		}
		LOG.debug("After check result");
		
		if(matchObject.isHomeMatch()){
			matchObject.setFremadGoals(Integer.parseInt(cols.get(URL_FIXTURES_RESULT).text().split(":")[0].trim()));
			matchObject.setOpposingTeamGoals(Integer.parseInt(cols.get(URL_FIXTURES_RESULT).text().split(":")[1].trim()));
		}else{
			matchObject.setFremadGoals(Integer.parseInt(cols.get(URL_FIXTURES_RESULT).text().split(":")[1].trim()));
			matchObject.setOpposingTeamGoals(Integer.parseInt(cols.get(URL_FIXTURES_RESULT).text().split(":")[0].trim()));		
		}

		return matchObject;
		
	}
	
	private static Elements getRowsFromTable(int leagueId){

		Elements rows = new Elements();
		try {
			URL url = new URL(URL_TABLE + leagueId);
			Document doc = Jsoup.parse(url, 1000000);
			
			Element table = doc.select("table").get(0); 
			rows = table.select("tr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	private static Elements getRowsFromFixture(int teamId){

		Elements rows = new Elements();
		try {
			URL url = new URL(URL_FIXTURE + teamId);
			Document doc = Jsoup.parse(url, 1000000);
			
			Element table = doc.select("table").get(0); 
			rows = table.select("tr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	private static int getTeamIdFromUrl(Element col){
		String link = col.select("a").first().attr("href");
		String pattern = "fiksId=";
		return Integer.parseInt(link.substring(link.lastIndexOf(pattern)+ pattern.length()));
	}
	
}

