library bug_list;

import 'bug.dart';

class BugList {
  bool empty;
  List<Bug> bugList;

  BugList(this.empty, this.bugList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": bugList
  };

  BugList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], new List<Bug>.from(json['listObject'].map((x) => new Bug.fromJson(x))));
}
