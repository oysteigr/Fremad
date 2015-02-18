library user_login_log;

class UserLoginLog {
  int userId;
  DateTime date;

  UserLoginLog(this.userId, this.date);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "userId": userId,
    "date": date
  };

  UserLoginLog.fromJson(Map<String, dynamic> json) : this(json['userId'],
      new DateTime.fromMillisecondsSinceEpoch(json['date'], isUtc: false));
}