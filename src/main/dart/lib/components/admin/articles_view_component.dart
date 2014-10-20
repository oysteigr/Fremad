part of fremad;

@Component(
    selector: 'admin-articles-view',
    templateUrl: 'packages/fremad/components/admin/articles_view.html',
    useShadowDom: false
)
class ShowAdminArticlesComponent {
  void create() {
    html.window.console.info("Is in ShowAdminArticlesComponent");
  }

}