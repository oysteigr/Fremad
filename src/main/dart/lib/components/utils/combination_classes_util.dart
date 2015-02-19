part of fremad;


class UserAndMeta{
  User user;
  UserMeta userMeta;
  
  UserAndMeta(this.user, this.userMeta);
}

class UserMetaAndLogin{
  UserMeta userMeta;
  List<UserLoginLog> loginList;
  
  UserMetaAndLogin(this.userMeta, this.loginList);
}

class ChangePassword{
  int id;
  String oldPassword;
  String newPassword;
 
  ChangePassword(this.id, this.oldPassword, this.newPassword);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "oldPassword": oldPassword,
    "newPassword": newPassword
  };
}