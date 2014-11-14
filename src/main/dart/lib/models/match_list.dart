library match_list;

import 'team.dart';
import 'match_entry.dart';

class MatchList {
  bool empty;
  List<MatchEntry> matchEntryList;

  MatchList(this.empty, this.matchEntryList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": matchEntryList
  };

  MatchList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<MatchEntry>.
      from(json['listObject'].map((x) => new MatchEntry.fromJson(x))));
}
