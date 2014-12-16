package fremad.processor;

import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fremad.domain.TableEntryObject;
import fremad.domain.list.TableEntryListObject;
import fremad.service.TableEntryService;


@Component
@Scope("request")
public class TableEntryProcessor {

	@Autowired
	TableEntryService tableEntryService;
	
	public TableEntryObject getTableEntry(int tableEntryId){
		return tableEntryService.getTableEntry(tableEntryId);
		
	}
	
	public TableEntryListObject getTableEntries(int leagueId){
		TableEntryListObject response = tableEntryService.getTableEntries(leagueId);
		if (response.size() > 0){
			Collections.sort(response.getList(), new Comparator<TableEntryObject>(){
				@Override
				public int compare(final TableEntryObject obj1, final TableEntryObject obj2){
					return Integer.valueOf(obj1.getPos()).compareTo(obj2.getPos());
				}
			});
		}
		return response;
	}
}