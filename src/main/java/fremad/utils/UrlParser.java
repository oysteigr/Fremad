package fremad.utils;

import fremad.domain.LeagueObject;
import fremad.domain.MatchObject;
import fremad.domain.TableEntryObject;
import fremad.domain.TeamObject;
import fremad.domain.list.MatchListObject;
import fremad.domain.list.TableEntryListObject;
import fremad.rest.TestResource;
import fremad.utils.UrlConstants;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UrlParser  extends UrlConstants{
	private static final Logger LOG = LoggerFactory.getLogger(UrlParser.class);
	
	public static TableEntryListObject getTableEntryListObject(LeagueObject leagueObject){
		LOG.info("In getTableEntryListObject");
		
		TableEntryListObject tableEntryListObject = new TableEntryListObject();
		
		tableEntryListObject.setLeagueObject(leagueObject);
		
		Elements rows = getRowsFromTable(leagueObject.getId());
		
		for (int i = 2; i < rows.size(); i++) { 
			tableEntryListObject.add(getTableEntryFromRow(rows.get(i), leagueObject.getId()));
		}
		
		return tableEntryListObject;
	}
	
	public static MatchListObject getMatchListObject(TeamObject teamObject){
		LOG.info("In getMatchListObject");
		
		MatchListObject matchListObject = new MatchListObject();
		
		Elements rows = getRowsFromFixture(teamObject.getOnlineId());
		
		for (int i = 1; i < rows.size(); i++) { 
			matchListObject.add(getMatchFromRow(rows.get(i), teamObject));
		}
		
		return matchListObject;
	}
	
	public static String getLeagueNameFromId(int id){
		try {
			String urlString = "http://www.fotball.no/System-pages/Tabell/?tournamentId=" + id;
			URL url = new URL(urlString);
			Document doc = Jsoup.parse(url, 1000000);
			
			Element header = doc.select("h2").get(0); //select the first h2 header.
			
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
	
	private static TableEntryObject getTableEntryFromRow(Element row, int leagueId){
		
		TableEntryObject tableEntryObject = new TableEntryObject();
		
		Elements cols = row.select("td");
		
		tableEntryObject.setLeagueId(leagueId);
		tableEntryObject.setPos(Integer.parseInt(cols.get(URL_TABLE_POS).text()));		
		tableEntryObject.setTeamId(getTeamIdFromUrl(cols.get(URL_TABLE_TEAM)));
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
	
	private static MatchObject getMatchFromRow(Element row, TeamObject teamObject){
		
		MatchObject matchObject = new MatchObject();
		
		Elements cols = row.select("td");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");	
		
		try {
			Date date = formatter.parse(cols.get(URL_FIXTURES_DATE).text() + " " + cols.get(URL_FIXTURES_TIME).text());
			matchObject.setDate(new Timestamp(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		matchObject.setFremadTeam(teamObject.getId());
		matchObject.setHomeMatch(teamObject.getOnlineId() == getTeamIdFromUrl(cols.get(URL_FIXTURES_HOMETEAM)));
		matchObject.setField(cols.get(URL_FIXTURES_FIELD).text());
		matchObject.setLeague(getTournementIdFromUrl(cols.get(URL_FIXTURES_TOURNEMENT)));
		
		if(matchObject.isHomeMatch()){
			matchObject.setOpposingTeamId(getTeamIdFromUrl(cols.get(URL_FIXTURES_AWAYTEAM)));
			matchObject.setOpposingTeamName(cols.get(URL_FIXTURES_AWAYTEAM).text());
		}else{
			matchObject.setOpposingTeamId(getTeamIdFromUrl(cols.get(URL_FIXTURES_HOMETEAM)));
			matchObject.setOpposingTeamName(cols.get(URL_FIXTURES_HOMETEAM).text());		
		}
		
		if(cols.get(URL_FIXTURES_RESULT).text().equals("Ikke klart")){
			return matchObject;
		}
		
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
			
			Element table = doc.select("table").get(1); 
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
	
	private static int getTournementIdFromUrl(Element col){
		String link = col.select("a").first().attr("href");
		String pattern = "tournamentId=";
		return Integer.parseInt(link.substring(link.lastIndexOf(pattern)+ pattern.length()));
	}
}
