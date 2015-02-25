library player_note;

class PlayerNote {
  int id;
  int playerId;
  String note;
  DateTime date;

  PlayerNote(this.id, this.playerId, this.note, this.date);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "playerId": playerId,
    "note": note,
    "date": date.millisecondsSinceEpoch
  };

  PlayerNote.fromJson(Map<String, dynamic> json) : this(json['id'],json['playerId'],
      json['note'], new DateTime.fromMillisecondsSinceEpoch(json['date'], isUtc: false));
}