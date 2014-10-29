library league_list;

import 'league.dart';

class LeagueList {
  bool empty;
  List<League> leagueList;

  LeagueList(this.empty, this.leagueList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": leagueList
  };

  LeagueList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], new List<League>.from(json['listObject'].map((x) => new League.fromJson(x))));
}
