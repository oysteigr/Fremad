part of fremad;

@Component(
    selector: 'about-view',
    templateUrl: 'packages/fremad/components/about_view.html',
    cssUrl: 'packages/fremad/components/about_view.css',
    useShadowDom: false
)
class ShowTheClubComponent {
  final Http _http;
  bool pagesLoaded = false;
  bool articleLoaded = false;
  
  Article article = new Article(-1, -1, new DateTime.now(), "NEWS", "", "", "", "",false);
      
  List<Page> pageList;
  
  ShowTheClubComponent(this._http, RouteProvider routeProvider){
    String articleUrl = routeProvider.parameters["articleUrl"];
    html.window.console.info("RouteProvider in news article found url: " + articleUrl);
    loadPages();
    loadArticle(articleUrl);
    
  }
  
  void loadPages() {
    html.window.console.info("Is in loadPages");
    pagesLoaded = false;
    _http.get('rest/article/getPublishedPages.json')
      .then((HttpResponse response) {
        print(response);
        PageList articleListObject = new PageList.fromJson(response.data);
        pageList = articleListObject.pageList;
        pagesLoaded = true;
        html.window.console.info("Success on loading pages");
      })
      .catchError((e) {
        print(e);
        pagesLoaded = false;
        html.window.console.info("Could not load rest/article/getPublishedPages.json");
      });
  } 
  
  void loadArticle(String articleUrl) {
    html.window.console.info("Is in loadArticles");
    articleLoaded = false;
    _http.post('rest/article/getArticleFromUrl.json', articleUrl)
      .then((HttpResponse response) {
        print(response);
        article = new Article.fromJson(response.data);
        articleLoaded = true;
        html.window.console.info("Success on loading article");
      })
      .catchError((e) {
        print(e);
        articleLoaded = false;
        html.window.console.info("Could not load rest/article/getArticleFromUrl.json");
      });
  } 

}