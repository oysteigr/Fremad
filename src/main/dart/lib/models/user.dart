library user;

import 'user_role_enum.dart';

//enum UserRoleEnum {ZERO, SUPPORTER, PLAYER, AUTHOR , EDITOR, ADMIN}

class User{
  int id;
  String userName;
  String password;
  String salt;
  int role;
  DateTime created;
  bool validated;
 
  User(this.id, this.userName, this.password, this.salt, this.role, this.created, this.validated);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "userName": userName,
    "password": password,
    "salt": salt,
    "role": role,
    "created": created,
    "validated": validated
  };

  User.fromJson(Map<String, dynamic> json) : this(json['id'], json['userName'],
      json['password'], json['salt'],json['role'], new DateTime.fromMillisecondsSinceEpoch(json['created'], isUtc: false), json['validated']);
  
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
    }
    return 0;
  }
  
}