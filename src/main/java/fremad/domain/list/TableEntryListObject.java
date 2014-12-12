package fremad.domain.list;

import javax.xml.bind.annotation.XmlRootElement;

import fremad.domain.LeagueObject;
import fremad.domain.TableEntryObject;


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
