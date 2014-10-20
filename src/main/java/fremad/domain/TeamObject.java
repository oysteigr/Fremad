package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TeamObject")
public class TeamObject {
	private int id;
	private String name;
	private int onlineId;
	
	public TeamObject(int id, String name, int onlineId) {
		this.id = id;
		this.name = name;
		this.onlineId = onlineId;
	}
	
	public TeamObject() {
	}
	
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
	public int getOnlineId() {
		return onlineId;
	}
	public void setOnlineId(int onlineId) {
		this.onlineId = onlineId;
	}
}
