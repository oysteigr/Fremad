library user_meta;


//enum UserRoleEnum {ZERO, SUPPORTER, PLAYER, AUTHOR , EDITOR, ADMIN}

class UserMeta{
  int userId;
  String firstName;
  String lastName;
  String phoneNumber;
  DateTime birthday;
  String homeTown;
  String profession;
 
  UserMeta(this.userId, this.firstName, this.lastName, this.phoneNumber, this.birthday, this.homeTown, this.profession);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "userId" : userId,
    "firstName" : firstName,
    "lastName" : lastName,
    "phoneNumber" : phoneNumber,
    "birthday" : birthday.millisecondsSinceEpoch,
    "homeTown" : homeTown,
    "profession" : profession
  };

  UserMeta.fromJson(Map<String, dynamic> json) : this(json['userId'], json['firstName'],
      json['lastName'], json['phoneNumber'], new DateTime.fromMillisecondsSinceEpoch(json['birthday'], isUtc: false), json['homeTown'], json['profession']);
  
  String getFullName(){
    return firstName + " " + lastName;
  }
}