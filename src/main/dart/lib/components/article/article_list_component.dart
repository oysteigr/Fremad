part of fremad;

@Component(
    selector: 'article-list-view',
    templateUrl: 'packages/fremad/components/article/article_list.html',
    cssUrl: 'packages/fremad/components/article/article_list.css',
    useShadowDom: false
)


class ShowArticleListComponent {
  final Http _http;
  bool articlesLoaded = false;
  
  List<Article> articleList;
  
  ShowArticleListComponent(this._http){
    html.window.console.info("Is in ShowArticleListComponent");
    loadArticles();
  }
  
  void loadArticles() {
    html.window.console.info("Is in loadArticles");
    articlesLoaded = false;
    _http.post('rest/article/getArticles.json', 'NEWS')
      .then((HttpResponse response) {
        print(response);
        ArticleList articleListObject = new ArticleList.fromJson(response.data);
        articleList = articleListObject.articleList;
        articlesLoaded = true;
        html.window.console.info("Success on loading articles");
      })
      .catchError((e) {
        print(e);
        articlesLoaded = false;
        html.window.console.info("Could not load rest/article/getArticles.json");
      });
  } 
  
  String getDateText(Article article){
    return DateTimeUtils.getDateText(article.date);
  }
  
}