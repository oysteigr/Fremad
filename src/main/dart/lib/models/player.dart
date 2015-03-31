library player;

class Player {
  int id;
  String firstName;
  String lastName;
  int number;
  int memberSince;
  String position;
  String preferredFoot;
  int team;
  bool active;

  Player(this.id, this.firstName, this.lastName, this.number, this.memberSince, this.position, this.preferredFoot, this.team, this.active);
  
  int get getID => this.id;

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "firstName": firstName,
    "lastName": lastName,
    "number": number,
    "memberSince": memberSince,
    "position": position,
    "preferredFoot": preferredFoot,
    "team": team,
    "active": active
  };

  Player.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['firstName'], json['lastName'], json['number'], json['memberSince'], 
      json['position'], json['preferredFoot'], json['team'], json['active']);
  
  String getNameString(){
    String nameString = "";
    firstName.trim();
    lastName.trim();
    List<String> stringList = firstName.split(" "); 
    for (String t in stringList) {
      nameString += t.substring(0,1) + ". ";
    };
    nameString += lastName;
    return nameString;
  }
  
  String getPosString(){
    switch(position){
      case "goalkeeper":
        return "Goalkeeper";
      case "defender":
        return "Defender";
      case "midfielder":
        return "Midfielder";
      case "attacker":
        return "Forward";
    }
    return "No pos";
  }
}