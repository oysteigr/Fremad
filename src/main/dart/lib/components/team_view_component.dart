part of fremad;

@Component(
    selector: 'team-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/team_view.html',
    useShadowDom: false
)
class ShowTeamComponent {
  int teamId = 0;
  
  ShowTeamComponent(RouteProvider routeProvider){
    teamId =_teamId(routeProvider);
    html.window.console.info("RouteProvider found id: " + teamId);
  }
  
  int _teamId(routeProvider) => routeProvider.parameters["teamId"];
}