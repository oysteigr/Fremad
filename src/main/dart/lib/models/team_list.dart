library team_list;

import 'team.dart';

class TeamList {
  bool empty;
  List<Team> teamList;

  TeamList(this.empty, this.teamList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": teamList
  };

  TeamList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], json['listObject']);
}
