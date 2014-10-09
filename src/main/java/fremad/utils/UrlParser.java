package fremad.utils;

import fremad.domain.LeagueObject;
import fremad.domain.TableEntryListObject;
import fremad.domain.TableEntryObject;
import fremad.utils.UrlConstants;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class UrlParser  extends UrlConstants{
	
	

	// final int teamId = 30296;
	// final int leagueId = 138835;
	
	public static TableEntryListObject getTableEntryListObject(LeagueObject leagueObject){
		
		TableEntryListObject tableEntryListObject = new TableEntryListObject();
		
		tableEntryListObject.setLeagueObject(leagueObject);
		
		Elements rows = getRowsFromTable(leagueObject.getId());
		
		for (int i = 2; i < rows.size(); i++) { 
			
			tableEntryListObject.add(getTableEntryFromRow(rows.get(i)));
			
			Element row = rows.get(i);
			
		}
		
		return tableEntryListObject;
	}
	
	private static TableEntryObject getTableEntryFromRow(Element row){
		
		TableEntryObject tableEntryObject = new TableEntryObject();
		
		Elements cols = row.select("td");
		
//		String link = cols.get(URL_TABLE_TEAM).select("a").first().attr("href");
//		String pattern = "fiksId=";
//		tableEntryObject.setTeamId(Integer.parseInt(link.substring(link.lastIndexOf(pattern)+ pattern.length())));
		
		tableEntryObject.setPos(Integer.parseInt(cols.get(URL_TABLE_POS).text()));		
		tableEntryObject.setTeamId(getTeamIdFromUrl(cols.get(URL_TABLE_TEAM)));
		tableEntryObject.setTeamName(cols.get(URL_TABLE_TEAM).text());
		tableEntryObject.setMatchCount(Integer.parseInt(cols.get(URL_TABLE_MATCHES).text()));
		//(cols.get(URL_TABLE_HOME_WIN).text());
		//(cols.get(URL_TABLE_HOME_TIED).text());
		//(cols.get(URL_TABLE_HOME_LOST).text());
		//(cols.get(URL_TABLE_HOME_GOALS).text());
		//(cols.get(URL_TABLE_AWAY_WIN).text());
		//(cols.get(URL_TABLE_AWAY_TIED).text());
		//(cols.get(URL_TABLE_AWAY_LOST).text());
		//(cols.get(URL_TABLE_AWAY_GOALS).text());
		tableEntryObject.setGamesWon(Integer.parseInt(cols.get(URL_TABLE_WIN).text()));
		tableEntryObject.setGamesTied(Integer.parseInt(cols.get(URL_TABLE_TIED).text()));
		tableEntryObject.setGamesLost(Integer.parseInt(cols.get(URL_TABLE_LOST).text()));
		tableEntryObject.setGoalsScored(Integer.parseInt(cols.get(URL_TABLE_GOALS).text().split("-")[0].trim()));
		tableEntryObject.setGoalsConceded(Integer.parseInt(cols.get(URL_TABLE_GOALS).text().split("-")[1].trim()));
		//(cols.get(URL_TABLE_DIFF).text());
		tableEntryObject.setPoints(Integer.parseInt(cols.get(URL_TABLE_POINTS).text()));
		

		return tableEntryObject;
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
}
