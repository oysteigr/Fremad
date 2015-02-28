part of fremad;

class IntValuePair{
  int key;
  int value;
  
  IntValuePair(this.key, this.value);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "key": key,
    "value": value
  };

  IntValuePair.fromJson(Map<String, dynamic> json) : this(json['key'], json['value']);

}

class IntValuePairList {
  bool empty;
  List<IntValuePair> intValuePairList;

  IntValuePairList(this.empty, this.intValuePairList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": intValuePairList
  };

  IntValuePairList.fromJson(Map<String, dynamic> json) : this(json['empty'], new List<IntValuePair>.
      from(json['listObject'].map((x) => new IntValuePair.fromJson(x))));
}