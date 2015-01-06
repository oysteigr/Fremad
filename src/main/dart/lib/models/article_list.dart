library article_list;

import 'article.dart';

class ArticleList {
  bool empty;
  List<Article> articleList;

  ArticleList(this.empty, this.articleList);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "emtpy" : empty,
    "listObject": articleList
  };

  ArticleList.fromJson(Map<String, dynamic> json) : this( 
      json['empty'], new List<Article>.from(json['listObject'].map((x) => new Article.fromJson(x))));
}
