part of fremad;

@Component(
    selector: 'team-about-view',
    templateUrl: 'packages/fremad/components/team/about_view.html',
    useShadowDom: false
)
class ShowTeamAboutComponent {
  int teamID;
  String teamString = "testing";
  
  ShowTeamAboutComponent(RouteProvider routeProvider){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in about found id: " + teamID.toString());
  }
}