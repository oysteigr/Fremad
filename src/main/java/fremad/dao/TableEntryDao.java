package fremad.dao;

import fremad.domain.TableEntryListObject;
import fremad.domain.TableEntryObject;


public interface TableEntryDao {
	public int deleteTableEntry(int tableEntryId);
	public int deleteTableEntries(int leagueId);
	public TableEntryListObject getTableEntries(int leagueId);
	public boolean addTableEntry(TableEntryObject tableEntry);
	public TableEntryObject getTableEntry(int tableEntryId);
}
