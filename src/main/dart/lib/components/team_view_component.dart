part of fremad;

@Component(
    selector: 'team-view',
    templateUrl: 'packages/fremad/components/team_view.html',
    cssUrl: 'packages/fremad/components/team_view.css',
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