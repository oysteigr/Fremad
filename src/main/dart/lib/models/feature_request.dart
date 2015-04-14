library feature_reqeust;

class FeatureReqeust{
  int id;
  int userId;
  String title;
  String description;
  DateTime date;
  bool done;
 
  FeatureReqeust(this.id, this.userId, this.title, this.description, this.date, this.done);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "userId": userId,
    "title": title,
    "description": description,
    "date": date.millisecondsSinceEpoch,
    "done": done
  };

  FeatureReqeust.fromJson(Map<String, dynamic> json) : this(json['id'], json['userId'], json['title'], 
      json['description'], new DateTime.fromMillisecondsSinceEpoch(json['date']), json['done']);

}
