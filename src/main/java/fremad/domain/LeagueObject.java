package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LeagueObject")
public class LeagueObject {
	private int id;
	private String name;
	private int year;
	private int team;
	
	public LeagueObject(int id, String name, int year, int team) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.team = team;
	}
	
	public LeagueObject() {
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	
	
	
}
