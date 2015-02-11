package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PlayerObject")
public class PlayerObject {
	private int id;
	private String firstName;
	private String lastName;
	private int number;
	private int memberSince;
	private String position;
	private String preferredFoot;
	private int team;
	private boolean active;
	
	public PlayerObject(int id, String firstName, String lastName, int number,
			int memberSince, String position, String preferredFoot, int team, boolean active) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.memberSince = memberSince;
		this.position = position;
		this.preferredFoot = preferredFoot;
		this.team = team;
		this.active = active;
	}
	
	public PlayerObject(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPreferredFoot() {
		return preferredFoot;
	}
	public void setPreferredFoot(String preferredFoot) {
		this.preferredFoot = preferredFoot;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(int memberSince) {
		this.memberSince = memberSince;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
