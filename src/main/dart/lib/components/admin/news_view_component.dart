part of fremad;

@Component(
    selector: 'admin-news-view',
    templateUrl: 'packages/fremad/components/admin/news_view.html',
    cssUrl: 'packages/fremad/components/admin/news_view.css',
    useShadowDom: false
)
class ShowAdminNewsComponent {
  final Http _http;
  bool articlesLoaded = false;
  bool isEditing = false;
  bool isAdding = false;

  bool showImageUpload = false;

  List<Article> articleList;

  String errorMessage = "";
  
  String text = 'Write <b>here</b><br> <img src="https://angularjs.org/img/AngularJS-large.png"/>  <br> This is an <i> image</i> ';
  
  Article currentArticle = new Article(-1, -1, new DateTime.now(), "NEWS", "", "", "", "",false);
  
  String imageTitle = "";
  
  bool useRatio = true;
  int xRatio = 610;
  int yRatio = 350;
  int minWith = 1000;
  int minHeight = 400;
  
  
  ShowAdminNewsComponent(this._http){
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
  
  void saveArticle() {
    html.window.console.info("Is in saveArticle");
    _http.post('rest/article/addArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        currentArticle = new Article.fromJson(response.data);
        html.window.console.info("Success on adding article");
        MESSAGE.addSuccessMessage("Article saved");
        articleList.add(currentArticle);
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/addArticle.json");
      });
  } 
  
  void updateArticle() {
    html.window.console.info("Is in updateArticle");
    _http.post('rest/article/updateArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        html.window.console.info("Success on updating article");
        MESSAGE.addSuccessMessage("Article updated");
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/updateArticle.json");
      });
  } 
  
  void deleteArticle() {
    html.window.console.info("Is in deleteArticle");
    _http.post('rest/article/deleteArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        back();
        Article articleResponse = new Article.fromJson(response.data);
        articleList.removeWhere((Article) => Article.id == articleResponse.id);
        html.window.console.info("Success on deleting article");
        MESSAGE.addSuccessMessage("Article deleted");
        
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/deleteArticle.json");
      });
  } 
  
  void unPublish(){
    currentArticle.published = false;
    updateArticle();
  }
  
  void publish(){
    currentArticle.published = true;
    updateArticle();
  }
  
  String getPublishedState(bool published){
    return published ? "published" : "unpublished"; 
  }
  
  void addNewArticle(){
    currentArticle = new Article(-1, -1, new DateTime.now(), "NEWS", "", "", "", "",false);
    isEditing = true;
  }
  
  void selectArticle(article){
    currentArticle = article;
    isEditing = true;
  }
  
  void showImageEditor(){
    showImageUpload = true;
  }
  
  void cancelImage(){
    showImageUpload = false;
  }
  
  void back(){
    currentArticle = new Article(-1, -1, new DateTime.now(), "NEWS", "", "", "", "",false);
    isEditing = false;
  }
}