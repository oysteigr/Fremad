library leauge;

class League {
  int id;
  int year;
  int team;

  League(this.id, this.year, this.team);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "year": year,
    "team": team
  };

  League.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['year'], json['team']);
}