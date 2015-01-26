library match_entry;

import 'package:intl/intl.dart';

class MatchEntry{
  int id;
  int league;
  int fremadTeam;
  bool homeMatch;
  int fremadGoals;
  String opposingTeamName;
  int opposingTeamId;
  int opposingTeamGoals;
  DateTime date;
  String field;
 
  MatchEntry(this.id, this.league, this.fremadTeam, this.homeMatch, this.fremadGoals, this.opposingTeamName, 
      this.opposingTeamId, this.opposingTeamGoals, this.date, this.field);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "league": league,
    "fremadTeam": fremadTeam,
    "homeMatch": homeMatch,
    "fremadGoals": fremadGoals,
    "opposingTeamName": opposingTeamName,
    "opposingTeamId": opposingTeamId,
    "opposingTeamGoals": opposingTeamGoals,
    "date": date,
    "field": field
  };

  MatchEntry.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['league'], json['fremadTeam'], json['homeMatch'], json['fremadGoals'], 
      json['opposingTeamName'], json['opposingTeamId'], json['opposingTeamGoals'], 
      new DateTime.fromMillisecondsSinceEpoch(json['date'], isUtc: false), json['field']);
  
  String getDateAsString(){
    return asDate.format(date);
  }
  
  String getTimeAsString(){
      return asTime.format(date);
  }
  
  String resultClass(){
    if(fremadGoals > opposingTeamGoals){
      return "win";
    } else if (fremadGoals == opposingTeamGoals){
      return "tied";
    }
    else{
      return "loss";
    }
  }
  
  final DateFormat asDate = new DateFormat('dd.MM.yyyy', 'fr_FR');
  
  final DateFormat asTime = new DateFormat('HH:mm', 'fr_FR');
  
}