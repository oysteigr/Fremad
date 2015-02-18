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