part of fremad;

@Component(
    selector: 'front-article',
    templateUrl: 'packages/fremad/components/front/front_article.html',
    cssUrl: 'packages/fremad/components/front/front_article.css',
    useShadowDom: false
)
class FrontArticleComponent {
  void create() {
    print("In this func");
  }

}