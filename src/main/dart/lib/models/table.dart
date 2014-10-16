library table;

import 'league.dart';
import 'table_entry.dart';

class Table {
  League league;
  List<TableEntry> tableEntryList;

  Table(this.league, this.tableEntryList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "leagueObject": league,
    "listObject": tableEntryList
  };

  Table.fromJson(Map<String, dynamic> json) : this(json['leagueObject'],
      json['listObject']);
}
