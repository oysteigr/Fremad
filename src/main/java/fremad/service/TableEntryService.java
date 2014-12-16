package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcTableEntryDao;
import fremad.domain.TableEntryObject;
import fremad.domain.list.TableEntryListObject;

@Service
@Scope("singleton")
public class TableEntryService {
	
	@Autowired
	JdbcTableEntryDao tableEntryDao;
	
	public TableEntryListObject getTableEntries(int leagueId){
		return tableEntryDao.getTableEntries(leagueId);
	}
	
	public TableEntryObject getTableEntry(int tableEntryId){
		return tableEntryDao.getTableEntry(tableEntryId);
	}
	
	public boolean addTableEntry(TableEntryObject tableEntry){
		return tableEntryDao.addTableEntry(tableEntry);
	}
	
	public int addTableEntries(TableEntryListObject tableEntryList) {
		int entriesAdded = 0;
		for (TableEntryObject tableEntry : tableEntryList) {
			if (tableEntryDao.addTableEntry(tableEntry)) {
				entriesAdded++;
			}
		}	
		return entriesAdded;
	}

}
