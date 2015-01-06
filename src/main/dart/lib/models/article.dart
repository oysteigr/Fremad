library article;

class Article {
  int id;
  int authorId;
  DateTime date;
  String articleType;
  String header;
  String context;
  String content;
  String imageURL;

  Article(this.id, this.authorId, this.date, this.articleType, this.header, this.context, this.content, this.imageURL);
  
  int get getID => this.id;

  Map<String, dynamic> toJson() => <String, dynamic>{
    "id": id,
    "authorId": authorId,
    "date": date,
    "articleType": articleType,
    "header": header,
    "context": context,
    "content": content,
    "imageURL": imageURL
  };

  Article.fromJson(Map<String, dynamic> json) : this(json['id'],
      json['authorId'], json['date'], json['articleType'], json['header'], 
      json['context'], json['content'], json['imageURL']);
  
}