part of fremad;

@Component(
    selector: 'team-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/team_view.html',
    useShadowDom: false
)
class ShowTeamComponent {
  void create() {
    html.window.console.info("Is in ShowTeamComponent");
  }
}