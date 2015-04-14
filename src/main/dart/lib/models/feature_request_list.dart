library feature_request_list;

import 'feature_request.dart';

class FeatureReqeustList {
  bool empty;
  List<FeatureReqeust> featureReqeustList;

  FeatureReqeustList(this.empty, this.featureReqeustList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": featureReqeustList
  };

  FeatureReqeustList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], new List<FeatureReqeust>.from(json['listObject'].map((x) => new FeatureReqeust.fromJson(x))));
}
