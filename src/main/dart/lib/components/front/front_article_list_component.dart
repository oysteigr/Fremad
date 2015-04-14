part of fremad;

@Component(
    selector: 'front-article-list',
    templateUrl: 'packages/fremad/components/front/front_article_list.html',
    cssUrl: 'packages/fremad/components/front/front_article_list.css',
    useShadowDom: false
)
class FrontArticleListComponent {
  final Http _http;
  
  bool articlesLoaded = false;
  
  List<Article> articleList = new List<Article>();
  Article selectedArticle = null;
  
  FrontArticleListComponent(this._http){
    loadArticles();

  }
  
  void selectArticle(int id){
    int index = articleList.indexOf(articleList.where((Article) => Article.id == id).first);
    selectedArticle = articleList[index];
  }
  
  void create() {
    print("In this func");
  }
  
  bool checkWidth(int width){
    return width < html.window.screen.width;
  }
  
  void loadArticles() {
    html.window.console.info("Is in loadArticles");
    articlesLoaded = false;
    _http.get('rest/article/getArticleHeadersShort.json')
      .then((HttpResponse response) {
        print(response);
        ArticleList articleListObject = new ArticleList.fromJson(response.data);
        articleList = articleListObject.articleList;
        articlesLoaded = true;
        if(articleList.length > 0){
          selectedArticle = articleList[0];
        }
        html.window.console.info("Success on loading articles");
      })
      .catchError((e) {
        print(e);
        articlesLoaded = false;
        html.window.console.info("Could not load rest/article/getArticleHeaders.json");
      });
  } 

}

/*int id;
int authorId;
DateTime date;
String articleType;
String header;
String context;
String content;
String imageURL;*/