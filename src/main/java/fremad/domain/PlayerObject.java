package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "PlayerObject")
public class PlayerObject {
	private int id;
	private String firstName;
	private String lastName;
	private String possition;
	private String prefferedFoot;
	private int memberSince;
	private int team;
	
	
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
	public String getPossition() {
		return possition;
	}
	public void setPossition(String possition) {
		this.possition = possition;
	}
	public String getPrefferedFoot() {
		return prefferedFoot;
	}
	public void setPrefferedFoot(String prefferedFoot) {
		this.prefferedFoot = prefferedFoot;
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
}
