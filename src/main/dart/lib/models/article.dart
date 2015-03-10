library article;

class Article {
  int id;
  int authorId;
  DateTime date;
  String articleType;
  String header;
  String context;
  String content;
  String imageUrl;
  bool published;

  Article(this.id, this.authorId, this.date, this.articleType, this.header, this.context, this.content, this.imageUrl, this.published);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "authorId": authorId,
    "date": date.millisecondsSinceEpoch,
    "articleType": articleType,
    "header": header,
    "context": context,
    "content": content,
    "imageUrl": imageUrl,
    "published": published
  };

  Article.fromJson(Map<String, dynamic> json) : this(json['id'], json['authorId'],  
      new DateTime.fromMillisecondsSinceEpoch(json['date'], isUtc: false), json['articleType'], 
      json['header'], json['context'], json['content'], json['imageUrl'], json['published']);
  
}