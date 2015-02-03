library user_meta;

import 'package:intl/intl.dart';

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
    "birthday" : asDate.format(birthday),
    "homeTown" : homeTown,
    "profession" : profession
  };

  UserMeta.fromJson(Map<String, dynamic> json) : this(json['userId'], json['firstName'],
      json['lastName'], json['phoneNumber'], DateTime.parse(json['birthday']), json['homeTown'], json['profession']);
  
  String getFullName(){
    return firstName + " " + lastName;
  }
  
  String getDateString(){
    String dateString = asDateString.format(birthday);
    if(dateString == '01.01.0001'){
      return '-'; 
    }
    return asDateString.format(birthday);
  }
  
  final DateFormat asDate = new DateFormat('yyyy-MM-dd', 'fr_FR');
  final DateFormat asDateString = new DateFormat('dd.MM.yyyy', 'fr_FR');
}