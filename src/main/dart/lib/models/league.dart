library leauge;

class League {
  int id;
  String name;
  int year;
  int team;

  League(this.id, this.name, this.year, this.team);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "name": name,
    "year": year,
    "team": team
  };

  League.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['name'], json['year'], json['team']);
}