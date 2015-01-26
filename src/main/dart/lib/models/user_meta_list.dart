library user_meta_list;

import 'user_meta.dart';

class UserMetaList {
  bool empty;
  List<UserMeta> userMetaList;

  UserMetaList(this.empty, this.userMetaList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": userMetaList
  };

  UserMetaList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<UserMeta>.
      from(json['listObject'].map((x) => new UserMeta.fromJson(x))));
}
