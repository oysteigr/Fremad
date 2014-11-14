part of fremad;

@Component(
    selector: 'team-players-view',
    templateUrl: 'packages/fremad/components/team/players_view.html',
    useShadowDom: false
)
class ShowTeamPlayersComponent {

  int teamID;
  
  ShowTeamPlayersComponent(RouteProvider routeProvider){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in players found id: " + teamID.toString());
  }
}