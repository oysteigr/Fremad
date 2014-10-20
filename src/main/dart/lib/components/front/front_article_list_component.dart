part of fremad;

@Component(
    selector: 'front-article-list',
    templateUrl: 'packages/fremad/components/front/front_article_list.html',
    cssUrl: 'packages/fremad/components/front/front_article_list.css',
    useShadowDom: false
)
class FrontArticleListComponent {
  void create() {
    print("In this func");
  }

}