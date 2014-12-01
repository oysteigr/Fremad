library player_list;

import 'player.dart';

class PlayerList {
  bool empty;
  List<Player> playerList;

  PlayerList(this.empty, this.playerList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": playerList
  };

  PlayerList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], new List<Player>.from(json['listObject'].map((x) => new Player.fromJson(x))));
}
