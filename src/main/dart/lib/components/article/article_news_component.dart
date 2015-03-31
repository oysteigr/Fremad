part of fremad;

@Component(
    selector: 'article-news-view',
    templateUrl: 'packages/fremad/components/article/article_news.html',
    cssUrl: 'packages/fremad/components/article/article_news.css',
    useShadowDom: false
)


class ShowArticleNewsComponent {
  final Http _http;
  bool articleLoaded = false;
  
  int articleId;
  
  Article article = new Article(-1, -1, new DateTime(1), "NEWS", "a", "b", "c", "d", true);
  
  ShowArticleNewsComponent(this._http, RouteProvider routeProvider){
    articleId = int.parse(routeProvider.parameters["articleId"]);
    html.window.console.info("RouteProvider in news article found id: " + articleId.toString());
    loadArticle();
  }
  
  void loadArticle() {
    html.window.console.info("Is in loadArticles");
    articleLoaded = false;
    _http.post('rest/article/getArticle.json', articleId.toString())
      .then((HttpResponse response) {
        print(response);
        article = new Article.fromJson(response.data);
        articleLoaded = true;
        html.window.console.info("Success on loading article");
      })
      .catchError((e) {
        print(e);
        articleLoaded = false;
        html.window.console.info("Could not load rest/article/getArticles.json");
      });
  } 
}