part of fremad;

@Component(
    selector: 'admin-matches-view',
    templateUrl: 'packages/fremad/components/admin/matches_view.html',
    useShadowDom: false
)
class ShowAdminMatchesComponent {
  void create() {
    html.window.console.info("Is in ShowAdminMatchesComponent");
  }

  
}