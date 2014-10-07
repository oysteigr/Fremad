package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "PlayerObject")
public class PlayerObject {
	private int id;
	private String name;
	private String possition;
	private String prefferedFoot;
	private int team;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

}
