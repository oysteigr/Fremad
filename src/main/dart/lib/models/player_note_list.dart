library player_note_list;

import 'player_note.dart';

class PlayerNoteList {
  bool empty;
  List<PlayerNote> playerNoteList;

  PlayerNoteList(this.empty, this.playerNoteList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": playerNoteList
  };

  PlayerNoteList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], new List<PlayerNote>.from(json['listObject'].map((x) => new PlayerNote.fromJson(x))));
}
