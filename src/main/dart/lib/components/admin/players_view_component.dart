part of fremad;

@Component(
    selector: 'admin-players-view',
    templateUrl: 'packages/fremad/components/admin/players_view.html',
    useShadowDom: false
)
class ShowAdminPlayersComponent {
  void create() {
    html.window.console.info("Is in ShowAdminPlayersComponent");
  }

}