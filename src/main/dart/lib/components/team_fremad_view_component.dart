part of fremad;

@Component(
    selector: 'team-fremad-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/team_fremad_view.html',
    useShadowDom: false
)
class ShowTeamFremadComponent {
  void create() {
    print("In this func");
  }

}