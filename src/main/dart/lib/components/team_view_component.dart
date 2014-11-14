part of fremad;

@Component(
    selector: 'team-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/team_view.html',
    useShadowDom: false
)
class ShowTeamComponent {
  String teamId = null;
  
  ShowTeamComponent(RouteProvider routeProvider){
    teamId =_teamId(routeProvider);
    html.window.console.info("RouteProvider found id: " + teamId.toString());
  }
  
  String _teamId(routeProvider) => routeProvider.parameters["teamId"];
}