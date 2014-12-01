library player;

class Player {
  int id;
  String firstName;
  String lastName;
  int number;
  int memberSince;
  String possition;
  String preferredFoot;
  int team;

  Player(this.id, this.firstName, this.lastName, this.number, this.memberSince, this.possition, this.preferredFoot, this.team);
  
  int get getID => this.id;

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "firstName": firstName,
    "lastName": lastName,
    "number": number,
    "memberSince": memberSince,
    "possition": possition,
    "preferredFoot": preferredFoot,
    "team": team
  };

  Player.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['firstName'], json['lastName'], json['number'], json['memberSince'], 
      json['possition'], json['preferredFoot'], json['team']);
}