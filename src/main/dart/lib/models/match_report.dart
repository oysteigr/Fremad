library match_report;

class MatchReport {
  int matchId; 
  int articleId;
  int homeScore;
  int awayScore;
  int homeScorePause;
  int awayScorePause;
  int supporters;
  bool published;

  MatchReport(this.matchId, this.articleId, this.homeScore, this.awayScore, this.homeScorePause, this.awayScorePause, this.supporters, this.published);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "matchId": matchId,
    "articleId": articleId,
    "homeScore": homeScore,
    "awayScore": awayScore,
    "homeScorePause": homeScorePause,
    "awayScorePause": awayScorePause,
    "supporters": supporters,
    "published": published
  };

  MatchReport.fromJson(Map<String, dynamic> json) : this(json['matchId'], json['articleId'],  
      json['homeScore'], json['awayScore'], json['homeScorePause'], json['awayScorePause'], json['supporters'], json['published']);
 
}

