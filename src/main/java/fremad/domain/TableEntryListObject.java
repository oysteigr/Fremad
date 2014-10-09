package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@XmlRootElement(name = "TableEntryListObject")
public class TableEntryListObject extends AbstractListWrapper<TableEntryObject>{
	
	private LeagueObject leagueObject;

	public LeagueObject getLeagueObject() {
		return leagueObject;
	}

	public void setLeagueObject(LeagueObject leagueObject) {
		this.leagueObject = leagueObject;
	}
	
}
