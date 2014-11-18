part of fremad;

@Component(
    selector: 'home-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/home_view.html',
    useShadowDom: false
)
class ShowHomeComponent {
  final Http _http;
  bool teamsLoaded = false;
  List<Team> teamList;
  TeamList teamListObject;
  
  ShowHomeComponent(this._http){
    loadTeams();
  }
  
  void loadTeams() {
    html.window.console.info("Is in loadTeams");
    teamsLoaded = false;
    _http.get('rest/team/getTeams.json')
      .then((HttpResponse response) {
        print(response);
        teamListObject = new TeamList.fromJson(response.data);
        teamList = teamListObject.teamList;
        teamsLoaded = true;
        html.window.console.info("Success on loading teams");
      })
      .catchError((e) {
        print(e);
        teamsLoaded = false;
        html.window.console.info("Could not load rest/team/getTeams.json");
      });
  } 
}