library table_entry;

class TableEntry {
  int id;
  int leagueId;
  int pos;
  String teamName;
  int teamId;
  int matchCount;
  int goalsScored;
  int goalsConceded;
  int points;
  int gamesWon;
  int gamesTied;
  int gamesLost;

  TableEntry(this.id, this.leagueId, this.pos, this.teamName, this.teamId,
      this.matchCount, this.goalsScored, this.goalsConceded, this.points, 
      this.gamesWon, this.gamesTied, this.gamesLost);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "leagueId": leagueId,
    "pos": pos,
    "teamName": teamName,
    "teamId": teamId,
    "matchCount": matchCount,
    "goalsScored": goalsScored,
    "goalsConceded": goalsConceded,
    "points": points,
    "gamesWon": gamesWon,
    "gamesTied": gamesTied,
    "gamesLost": gamesLost
  };

  TableEntry.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['leagueId'], json['pos'], json['teamName'], json['teamId'],
      json['matchCount'], json['goalsScored'], json['goalsConceded'], 
      json['points'], json['gamesWon'], json['gamesTied'], json['gamesLost']);
}