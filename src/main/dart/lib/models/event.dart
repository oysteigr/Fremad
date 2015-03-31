library event;

class Event{
  
  int id;
  int player;
  int eventType;
  int matchTime;
  int matchId;
 
  Event(this.id, this.player, this.eventType, this.matchTime, this.matchId);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "player": player,
    "eventType": eventType,
    "matchTime": matchTime,
    "matchId": matchId,
  };

  Event.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['player'], json['eventType'], json['matchTime'], json['matchId']);

}