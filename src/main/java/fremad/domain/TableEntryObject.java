package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@XmlRootElement(name = "TableEntryObject")
public class TableEntryObject {
	private int id;
	private int leagueId;
	private int pos;
	private String teamName;
	private int teamId;
	private int matchCount;
	private int goalsScored;
	private int goalsConceded;
	private int points;
	private int gamesWon;
	private int gamesTied;
	private int gamesLost;
	
	public TableEntryObject() {
		super();
	}
	
	public TableEntryObject(int id, int leagueId, int pos, String teamName,
			int teamId, int matchCount, int goalsScored, int goalsConceded,
			int points, int gamesWon, int gamesTied, int gamesLost) {
		super();
		this.id = id;
		this.leagueId = leagueId;
		this.pos = pos;
		this.teamName = teamName;
		this.teamId = teamId;
		this.matchCount = matchCount;
		this.goalsScored = goalsScored;
		this.goalsConceded = goalsConceded;
		this.points = points;
		this.gamesWon = gamesWon;
		this.gamesTied = gamesTied;
		this.gamesLost = gamesLost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getMatchCount() {
		return matchCount;
	}
	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}
	public int getGoalsScored() {
		return goalsScored;
	}
	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}
	public int getGoalsConceded() {
		return goalsConceded;
	}
	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getGamesWon() {
		return gamesWon;
	}
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	public int getGamesTied() {
		return gamesTied;
	}
	public void setGamesTied(int gamesTied) {
		this.gamesTied = gamesTied;
	}
	public int getGamesLost() {
		return gamesLost;
	}
	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
	
	
}
