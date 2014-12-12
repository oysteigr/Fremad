package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "TeamListObject")
public class TeamListObject extends AbstractListWrapper<TeamObject>{
	
	private TeamObject teamObject;

	public TeamObject getTeamObject() {
		return teamObject;
	}

	public void setTeamObject(TeamObject teamObject) {
		this.teamObject = teamObject;
	}

}
