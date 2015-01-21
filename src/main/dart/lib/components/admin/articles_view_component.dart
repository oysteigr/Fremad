part of fremad;

@Component(
    selector: 'admin-articles-view',
    templateUrl: 'packages/fremad/components/admin/articles_view.html',
    useShadowDom: false
)
class ShowAdminArticlesComponent {
  final Http _http;
  bool teamsLoaded;
  bool playersLoaded;
  bool isEditing = false;
  bool isAdding = false;
  int selectedPlayer = -1;
  TeamList teamListObject;
  List<Team> teamList;
  PlayerList playerListObject;
  List<Player> playerList;
  Article articlePlayer = new Article(-1, -1, new DateTime.now(), "", "", "", "", "",false);
  
  ShowAdminArticlesComponent(this._http){
    loadArticles();
    loadUsers();
  }
  
  void loadArticles() {
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
  
  void loadUsers(){
    
  }
}