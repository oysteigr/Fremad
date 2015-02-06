library role_request;

import 'package:intl/intl.dart';
import 'user_role_enum.dart';


//enum UserRoleEnum {ZERO, SUPPORTER, PLAYER, AUTHOR , EDITOR, ADMIN}

class RoleRequest{
  int id;
  int userId;
  int requestedRole;
  DateTime date;
  bool accepted;
  int acceptedByUser;
 
  RoleRequest(this.id, this.userId, this.requestedRole, this.date, this.accepted, this.acceptedByUser);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "userId": userId,
    "requestedRole": requestedRole,
    "date": date.millisecondsSinceEpoch,
    "accepted": accepted,
    "acceptedByUser": acceptedByUser
  };

  RoleRequest.fromJson(Map<String, dynamic> json) : this(json['id'], json['userId'], 
      json['requestedRole'], new DateTime.fromMillisecondsSinceEpoch(json['date'], isUtc: false), json['accepted'], json['acceptedByUser']);
  
  static int parseEnum(String name){
    switch(name){
      case "SUPPORTER":
        return 1;
      case "PLAYER":
        return 2;
      case "AUTHOR":
        return 3;
      case "EDITOR":
        return 4;
      case "ADMIN":
        return 5;
      case "SUPER":
        return 6;
    }
    return 0;
  }
  
  static String parseRole(int role){
    switch(role){
      case 1:
        return "SUPPORTER";
      case 2:
        return "PLAYER";
      case 3:
        return "AUTHOR";
      case 4:
        return "EDITOR";
      case 5:
        return "ADMIN";
      case 6:
        return "SUPER";
    }
    return "No role";
  }

  
  final DateFormat asDate = new DateFormat('dd.MM.yyyy', 'fr_FR');
  
  final DateFormat asTime = new DateFormat('HH:mm', 'fr_FR');
  
  
  String getDateString(){
    return asDate.format(date);
  }
    
}