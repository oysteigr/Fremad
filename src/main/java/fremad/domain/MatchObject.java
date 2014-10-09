package fremad.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchObject")
public class MatchObject {
	private int id;
	private int league;
	private int fremad_team;
	private boolean homeMatch;
	private int homeGoals;
	private String opposingTeamName;
	private int opposingTeamId;
	private int opposingTeamGoals;
	private Date date;
	private String field;
	
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
	public int getHomeGoals() {
		return homeGoals;
	}
	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	
}
