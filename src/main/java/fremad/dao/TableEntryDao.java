package fremad.dao;

import fremad.domain.TableEntryObject;
import fremad.domain.list.TableEntryListObject;


public interface TableEntryDao {
	public int deleteTableEntry(int tableEntryId);
	public int deleteTableEntries(int leagueId);
	public TableEntryListObject getTableEntries(int leagueId);
	public boolean addTableEntry(TableEntryObject tableEntry);
	public boolean updateTableEntry(TableEntryObject tableEntry);
	public TableEntryObject getTableEntry(int tableEntryId);
}
