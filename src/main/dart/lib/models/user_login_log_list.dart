library user_login_log_list;

import 'user_login_log.dart';

class UserLoginLogList {
  bool empty;
  List<UserLoginLog> userLoginLogList;

  UserLoginLogList(this.empty, this.userLoginLogList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": userLoginLogList
  };

  UserLoginLogList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<UserLoginLog>.
      from(json['listObject'].map((x) => new UserLoginLog.fromJson(x))));
}