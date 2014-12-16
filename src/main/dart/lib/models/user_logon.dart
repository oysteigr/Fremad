library user_logon;

class UserLogon{
  String userName;
  String password;
 
  UserLogon(this.userName, this.password);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "userName": userName,
    "password": password
  };

  UserLogon.fromJson(Map<String, dynamic> json) : this(json['userName'], json['password']);
  
}