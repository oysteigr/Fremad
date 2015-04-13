library bug;

class Bug{
  
  int id;
  int userId;
  int priority;
  String title;
  String os;
  String browser;
  String problem;
  DateTime date;
  bool fixed;
 
  Bug(this.id, this.userId, this.priority, this.title, this.os, this.browser, this.problem, this.date, this.fixed);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "userId": userId,
    "priority": priority,
    "title": title,
    "os": os,
    "browser": browser,
    "problem": problem,
    "date": date.millisecondsSinceEpoch,
    "fixed": fixed
  };

  Bug.fromJson(Map<String, dynamic> json) : this(json['id'], json['userId'], json['priority'], json['title'], json['os'], 
      json['browser'], json['problem'], new DateTime.fromMillisecondsSinceEpoch(json['date']), json['fixed']);

}