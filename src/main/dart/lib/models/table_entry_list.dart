library table_entry_list;

import 'table_entry.dart';

class TableEntryList {
  bool empty;
  List<TableEntry> tableEntryList;

  TableEntryList(this.empty, this.tableEntryList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": tableEntryList
  };

  TableEntryList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<TableEntry>.
      from(json['listObject'].map((x) => new TableEntry.fromJson(x))));
}
