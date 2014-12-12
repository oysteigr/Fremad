package fremad.domain.list;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.TeamObject;


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
