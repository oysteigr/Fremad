package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "MatchListObject")
public class MatchListObject extends AbstractListWrapper<MatchObject>{
	
	private TeamObject teamObject;

	public TeamObject getTeamObject() {
		return teamObject;
	}

	public void setTeamObject(TeamObject teamObject) {
		this.teamObject = teamObject;
	}

}
