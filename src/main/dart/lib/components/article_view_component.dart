part of fremad;

@Component(
    selector: 'article-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/article_view.html',
    useShadowDom: false
)
class ShowArticleComponent {
  
  ShowArticleComponent(){
    html.window.console.debug("In ShowArticleComponent");
  }
  void create() {
    print("In this func");
  }

}