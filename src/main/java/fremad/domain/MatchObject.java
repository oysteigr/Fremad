package fremad.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MatchObject")
public class MatchObject {
	private int id;
	private int league;
	private int fremad_team;
	private boolean homeMatch;
	private String opposingTeamName;
	private int opposingTeamId;
	private Date date;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
