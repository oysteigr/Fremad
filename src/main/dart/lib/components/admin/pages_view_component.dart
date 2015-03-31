part of fremad;

@Component(
    selector: 'admin-pages-view',
    templateUrl: 'packages/fremad/components/admin/pages_view.html',
    cssUrl: 'packages/fremad/components/admin/pages_view.css',
    useShadowDom: false
)
class ShowAdminPagesComponent {
  final Http _http;
  bool articlesLoaded = false;
  bool pagesLoaded = false;
  
  bool isEditing = false;
  bool isAdding = false;

  List<Article> articleList;
  List<Page> pageList;

  String errorMessage = "";
  
  String text = 'Write <b>here</b><br> <img src="https://angularjs.org/img/AngularJS-large.png"/>  <br> This is an <i> image</i> ';
  
  Article currentArticle = new Article(-1, -1, new DateTime.now(), "NEWS", "", "", "", "",false);
  Page currentPage = new Page(-1,"","",-1,false);
 
  
  
  ShowAdminPagesComponent(this._http){
    loadArticles();
    loadPages();
  }
  
  void loadArticles() {
    html.window.console.info("Is in loadArticles");
    articlesLoaded = false;
    _http.post('rest/article/getArticles.json', 'PAGE')
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
  
  void loadPages() {
    html.window.console.info("Is in loadPages");
    pagesLoaded = false;
    _http.get('rest/article/getPages.json')
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
        html.window.console.info("Could not load rest/article/getPages.json");
      });
  } 
  
  void saveArticle() {
    html.window.console.info("Is in saveArticle");
    _http.post('rest/article/addArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        currentArticle = new Article.fromJson(response.data);
        html.window.console.info("Success on adding article");
        savePage(currentArticle.id);
        articleList.add(currentArticle);
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/addArticle.json");
      });
  } 
  
  void savePage(int articleId) {
    html.window.console.info("Is in savePage");
    String url = currentArticle.header.trim().toLowerCase().replaceAll(" ", "-");
    html.window.console.info("Done trim");
    currentPage = new Page(articleId, currentArticle.header, url, currentPage.priority, currentArticle.published); 
    _http.post('rest/article/addPage.json', currentPage)
      .then((HttpResponse response) {
        print(response);
        currentPage = new Page.fromJson(response.data);
        html.window.console.info("Success on adding article");
        MESSAGE.addSuccessMessage("Page added");
        pageList.add(currentPage);
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/addPage.json");
      });
  } 
  
  void updateArticle() {
    html.window.console.info("Is in updateArticle");
    _http.post('rest/article/updateArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        updatePage();
        html.window.console.info("Success on updating article");
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/updateArticle.json");
      });
  } 
  
  void updatePage() {
    html.window.console.info("Is in updatePage");
    copyCurrentPageInfo();
    _http.post('rest/article/updatePage.json', currentPage)
      .then((HttpResponse response) {
        print(response);
        MESSAGE.addSuccessMessage("Page updated");
        MESSAGE.addInfoMessage("You need to reload browser for changes to take effect");
        html.window.console.info("Success on updating page");
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/updatePage.json");
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
    currentArticle = new Article(-1, -1, new DateTime.now(), "PAGE", "", "", "", "",false);
    currentPage = new Page(-1,"","",pageList.length,false);
    isEditing = true;
  }
  
  void selectArticle(Page page){
    currentArticle = articleList.where((Article) => Article.id == page.articleId).first;
    currentPage = pageList.where((Page) => Page.articleId == currentArticle.id).first;
    isEditing = true;
  }
  
  void back(){
    currentArticle = new Article(-1, -1, new DateTime.now(), "PAGE", "", "", "", "",false);
    currentPage = new Page(-1,"","",-1,false);
    isEditing = false;
  }
  
  void copyCurrentPageInfo(){
    String url = currentArticle.header.trim().toLowerCase().replaceAll(" ", "-");
    html.window.console.info("Done trim");
    currentPage.articleTitle = currentArticle.header;
    currentPage.urlName = url;
    currentPage.published = currentArticle.published;
  }
}