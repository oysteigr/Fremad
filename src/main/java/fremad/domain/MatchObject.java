package fremad.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchObject")
public class MatchObject {
	private int id;
	private int league;
	private int fremad_team;
	private boolean homeMatch;
	private int fremadGoals;
	private String opposingTeamName;
	private int opposingTeamId;
	private int opposingTeamGoals;
	private Timestamp date;
	private String field;
	
	
	
	public MatchObject(int id, int league, int fremad_team, boolean homeMatch,
			int fremadGoals, String opposingTeamName, int opposingTeamId,
			int opposingTeamGoals, Timestamp date, String field) {
		this.id = id;
		this.league = league;
		this.fremad_team = fremad_team;
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
	public int getFremad_team() {
		return fremad_team;
	}
	public void setFremad_team(int fremad_team) {
		this.fremad_team = fremad_team;
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
