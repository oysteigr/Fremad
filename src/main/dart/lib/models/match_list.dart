library match_list;

import 'team.dart';
import 'match_entry.dart';

class MatchList {
  Team team;
  bool empty;
  List<MatchEntry> matchEntryList;

  MatchList(this.team, this.empty, this.matchEntryList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "teamObject": team,
    "emtpy" : empty,
    "listObject": matchEntryList
  };

  MatchList.fromJson(Map<String, dynamic> json) : this(json['teamObject'], 
      json['empty'], json['listObject']);
}
