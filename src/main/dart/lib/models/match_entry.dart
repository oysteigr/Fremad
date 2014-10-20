library match_entry;

class MatchEntry{
  int id;
  int league;
  int fremad_team;
  bool homeMatch;
  int fremadGoals;
  String opposingTeamName;
  int opposingTeamId;
  int opposingTeamGoals;
  int date;
  String field;
 
  MatchEntry(this.id, this.league, this.fremad_team, this.homeMatch, this.fremadGoals, this.opposingTeamName, 
      this.opposingTeamId, this.opposingTeamGoals, this.date, this.field);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "league": league,
    "fremad_team": fremad_team,
    "homeMatch": homeMatch,
    "fremadGoals": fremadGoals,
    "opposingTeamName": opposingTeamName,
    "opposingTeamId": opposingTeamId,
    "opposingTeamGoals": opposingTeamGoals,
    "date": date,
    "field": field
  };

  MatchEntry.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['league'], json['fremad_team'], json['homeMatch'], json['fremadGoals'], 
      json['opposingTeamName'], json['opposingTeamId'], json['opposingTeamGoals'], 
      json['date'], json['field']);
  
}