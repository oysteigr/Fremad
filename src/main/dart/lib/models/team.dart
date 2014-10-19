library team;

class Team{
  int id;
  String name;
  int onlineId;
 
  Team(this.id, this.name, this.onlineId);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "name": name,
    "onlineId": onlineId
  };

  Team.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['name'], json['onlineId']);
  
}