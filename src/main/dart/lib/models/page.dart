library page;

class Page {
  int articleId;
  String articleTitle;
  String urlName;
  int priority;
  bool published;

  Page(this.articleId, this.articleTitle, this.urlName, this.priority, this.published);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "articleId": articleId,
    "articleTitle": articleTitle,
    "urlName": urlName,
    "priority": priority,
    "published": published
  };

  Page.fromJson(Map<String, dynamic> json) : this(json['articleId'], json['articleTitle'],  
      json['urlName'], json['priority'], json['published']);
 
}

