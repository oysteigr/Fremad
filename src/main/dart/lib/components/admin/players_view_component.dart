part of fremad;

@Component(
    selector: 'admin-players-view',
    templateUrl: 'packages/fremad/components/admin/players_view.html',
    cssUrl: 'packages/fremad/components/admin/players_view.css',
    useShadowDom: false
)

class ShowAdminPlayersComponent {
  final Http _http;
  bool tableLoaded;
  bool isEditing = false;
  int team = -1;
  TeamList teamListObject;
  List<Team> teamList;
  
  ShowAdminPlayersComponent(this._http){
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
  
  void create() {
    html.window.console.info("Is in ShowAdminPlayersComponent");
  }
  
  void addPlayer(){
    
  }
  
  void setEditingMode(){
    html.window.console.info("Is in setEditingMode");
    isEditing = true;
  }
  
  void cancel(){
    isEditing = false;
  }
}