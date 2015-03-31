library page_list;

import 'page.dart';

class PageList {
  bool empty;
  List<Page> pageList;

  PageList(this.empty, this.pageList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": pageList
  };

  PageList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<Page>.
      from(json['listObject'].map((x) => new Page.fromJson(x))));
}
