library match_list;

import 'team.dart';
import 'match_entry.dart';

class MatchList {
  Team team;
  List<MatchEntry> matchEntryList;

  MatchList(this.team, this.matchEntryList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "teamObject": team,
    "listObject": matchEntryList
  };

  MatchList.fromJson(Map<String, dynamic> json) : this(json['teamObject'],
      json['listObject']);
}
