library match_report_list;

import 'match_report.dart';

class MatchReportList {
  bool empty;
  List<MatchReport> matchReportList;

  MatchReportList(this.empty, this.matchReportList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": matchReportList
  };

  MatchReportList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<MatchReport>.
      from(json['listObject'].map((x) => new MatchReport.fromJson(x))));
}
