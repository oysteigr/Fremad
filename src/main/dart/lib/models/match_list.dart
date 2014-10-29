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

  MatchList.fromJson(Map<String, dynamic> json) : this(new Team.fromJson(json['teamObject']), 
      json['empty'], new List<MatchEntry>.from(json['listObject'].map((x) => new MatchEntry.fromJson(x))));
}
