part of fremad;

@Component(
    selector: 'team-players-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/team/players_view.html',
    useShadowDom: false
)
class ShowTeamPlayersComponent {
  void create() {
    print("In this func");
  }

}