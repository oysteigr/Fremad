library role_request_list;

import 'role_request.dart';

class RoleRequestList {
  bool empty;
  List<RoleRequest> roleRequestList;

  RoleRequestList(this.empty, this.roleRequestList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": roleRequestList
  };

  RoleRequestList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<RoleRequest>.
      from(json['listObject'].map((x) => new RoleRequest.fromJson(x))));
}