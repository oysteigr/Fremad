part of fremad;

@Component(
    selector: 'main-menu',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/main_menu.html',
    useShadowDom: false
)
class MainMenuComponent {
  final Http _http;
  bool tableLoaded = false;
  List<Team> teamList;
  TeamList teamListObject;
  
  MainMenuComponent(this._http){
    loadTeams();
  }
  void loadTeams() {
    html.window.console.info("Is in loadTeams");
    tableLoaded = false;
    _http.get('rest/team/getTeams.json')
      .then((HttpResponse response) {
        print(response);
        teamListObject = new TeamList.fromJson(response.data);
        teamList = teamListObject.teamList;
        tableLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        tableLoaded = false;
        html.window.console.info("Could not load rest/team/getTeams.json");
      });
  } 
}