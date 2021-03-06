package fremad.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchObject")
public class MatchObject {
	private int id;
	private int league;
	private int fremadTeam;
	private boolean homeMatch;
	private int fremadGoals;
	private String opposingTeamName;
	private int opposingTeamId;
	private int opposingTeamGoals;
	private Timestamp date;
	private String field;
	
	
	
	public MatchObject(int id, int league, int fremadTeam, boolean homeMatch,
			int fremadGoals, String opposingTeamName, int opposingTeamId,
			int opposingTeamGoals, Timestamp date, String field) {
		this.id = id;
		this.league = league;
		this.fremadTeam = fremadTeam;
		this.homeMatch = homeMatch;
		this.fremadGoals = fremadGoals;
		this.opposingTeamName = opposingTeamName;
		this.opposingTeamId = opposingTeamId;
		this.opposingTeamGoals = opposingTeamGoals;
		this.date = date;
		this.field = field;
	}
	
	public MatchObject() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeague() {
		return league;
	}
	public void setLeague(int league) {
		this.league = league;
	}
	public int getFremadTeam() {
		return fremadTeam;
	}
	public void setFremadTeam(int fremadTeam) {
		this.fremadTeam = fremadTeam;
	}
	public boolean isHomeMatch() {
		return homeMatch;
	}
	public void setHomeMatch(boolean homeMatch) {
		this.homeMatch = homeMatch;
	}
	public int getFremadGoals() {
		return fremadGoals;
	}
	public void setFremadGoals(int fremadGoals) {
		this.fremadGoals = fremadGoals;
	}
	public String getOpposingTeamName() {
		return opposingTeamName;
	}
	public void setOpposingTeamName(String opposingTeamName) {
		this.opposingTeamName = opposingTeamName;
	}
	public int getOpposingTeamId() {
		return opposingTeamId;
	}
	public void setOpposingTeamId(int opposingTeamId) {
		this.opposingTeamId = opposingTeamId;
	}	
	public int getOpposingTeamGoals() {
		return opposingTeamGoals;
	}
	public void setOpposingTeamGoals(int opposingTeamGoals) {
		this.opposingTeamGoals = opposingTeamGoals;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
}
