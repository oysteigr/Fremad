part of fremad;

@Component(
    selector: 'admin-leagues-view',
    templateUrl: 'packages/fremad/components/admin/leagues_view.html',
    cssUrl: 'packages/fremad/components/admin/leagues_view.css',
    useShadowDom: false
)
class ShowAdminLeaguesComponent {
  final Http _http;
  bool tableLoaded;
  int id = 0;
  String name = "no name";
  int year = 0;
  int team = -1;
  
  LeagueList leagueListObject;
  List<League> leagueList;
  TeamList teamListObject;
  List<Team> teamList;
  int selectedLeague = -1;
  bool isEditing = false;
  
  
  ShowAdminLeaguesComponent(this._http){
    loadLeagues();
    loadTeams();

 //   leagueList.add(new League(2166, 2013, 0));
  }
  
  void loadLeagues() {
    html.window.console.info("Is in loadLeagues");
    tableLoaded = false;
    _http.get('rest/league/getLeagues.json')
      .then((HttpResponse response) {
        print(response);
        leagueListObject = new LeagueList.fromJson(response.data);
        leagueList = leagueListObject.leagueList;
        tableLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        tableLoaded = false;
        html.window.console.info("Could not load rest/league/getLeagues.json");
      });
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
  
  void addLeague(){
    League league = new League(id, name, year, team);
    _http.post('rest/league/addLeague.json', JSON.encode(league))
    .then((HttpResponse response) {
      leagueList.add(new League.fromJson(response.data));
      id = 0;
      year = 0;
      team = -1;
      isEditing = false;
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/league/addLeague.json");
    });
    html.window.console.info("Added league: " + id.toString() + " succeded!");
  }
  
  void updateLeague(int id, String name, int year, int team){
    html.window.console.info("In update()");
    League tempLeague = new League(id, name, year, team);
    /*   for(int i = 0; i < teamList.length; i++){
      html.window.console.info("For in updateTeam size=" + teamList.length.toString());
      if(teamList.elementAt(i).id == selectedTeam){
        html.window.console.info("For in updateTeam2");
        tempTeam = teamList.elementAt(i);
      }
    }*/
    html.window.console.info("After for in updateTeam");
    html.window.console.info("Where in updateTeam: " + tempLeague.id.toString() + " succeded!");
    _http.post('rest/league/updateLeague.json', JSON.encode(tempLeague))
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/league/updateLeague.json");
    });
    selectedLeague = -1;
    html.window.console.info("Updating league: " + id.toString() + " succeded!");
  }
  
  void cancel(){
    id = 0;
    year = 0;
    team = -1;
    isEditing = false;
    html.window.console.info("Cancel");
  }
  
  void deleteLeague(int id, String name, int year, int team){
    html.window.console.info("In delete()");
    League tempLeague = new League(id, name, year, team);
    html.window.console.info("Before");
    League league1 = leagueList.firstWhere((League league) => id == league.id);
    html.window.console.info("After");
    /*   for(int i = 0; i < teamList.length; i++){
      if(teamList.elementAt(i).id == selectedTeam){
        tempTeam = teamList.elementAt(i);
      }
    }*/
    _http.post('rest/league/deleteLeague.json', JSON.encode(tempLeague))
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/league/deleteLeague.json");
    });
    selectedLeague = -1;
    html.window.console.info("Deleting league: " + id.toString() + " succeded!");
  }
  
  void selectLeague(int id){
    html.window.console.info("Selected team: " + id.toString());
    if(selectedLeague == id){
      selectedLeague = -1;
    } else {
      selectedLeague = id;
    }
  }
  
  void setEditingMode(){
    isEditing = !isEditing;
  }
  
  bool isActive(int id){
   return selectedLeague != id;
  }
  
  bool isAdding(){
    return !isEditing;
  }
}