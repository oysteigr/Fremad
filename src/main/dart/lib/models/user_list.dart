library user_list;

import 'user.dart';

class UserList {
  bool empty;
  List<User> userList;

  UserList(this.empty, this.userList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": userList
  };

  UserList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<User>.
      from(json['listObject'].map((x) => new User.fromJson(x))));
}
