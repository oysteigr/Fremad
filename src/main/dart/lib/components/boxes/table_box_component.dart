part of fremad;

@Component(
    selector: 'box-table-view',
    templateUrl: 'packages/fremad/components/boxes/table_box.html',
    cssUrl: 'packages/fremad/components/boxes/table_box.css',
    useShadowDom: false
)
class ShowBoxTableComponent implements AttachAware{
  
  @NgOneWayOneTime("teamId")
  int ID;
  
  final Http _http;
  bool tableEntriesLoaded = false;
  bool leaguesLoaded = false;
  bool teamLoaded = false;
  String message;
  Team team;
  TableEntryList tableEntryListObject;
  List<TableEntry> tableEntryList;
  LeagueList leagueListObject;
  List<League> leagueList;
  
  ShowBoxTableComponent(this._http, RouteProvider routeProvider){
 
  }
  
  void loadTableEntries() {
    html.window.console.info("Is in loadLeagues");
    tableEntriesLoaded = false;
    _http.post('rest/tableEntry/getTableEntries.json', JSON.encode(leagueList.first.getID))
      .then((HttpResponse response) {
        print(response);
        tableEntryListObject = new TableEntryList.fromJson(response.data);
        tableEntryList = tableEntryListObject.tableEntryList;
        tableEntriesLoaded = true;
        html.window.console.info("Success on loading tableEntries");
      })
      .catchError((e) {
        print(e);
        tableEntriesLoaded = false;
        html.window.console.info("Could not load rest/league/getLeagues.json");
      });
  } 
  void loadLeagues() {
    html.window.console.info("Is in loadLeagues");
    leaguesLoaded = false;
    _http.post('rest/league/getLeaguesByTeam.json', JSON.encode(ID))
      .then((HttpResponse response) {
        print(response);
        leagueListObject = new LeagueList.fromJson(response.data);
        leagueList = leagueListObject.leagueList;
        leaguesLoaded = true;
        html.window.console.info("Success on loading leagues");
        loadTableEntries();
      })
      .catchError((e) {
        print(e);
        leaguesLoaded = false;
        html.window.console.info("Could not load rest/league/getLeagues.json");
      });
  } 
  
  void loadTeam(){
    html.window.console.info("Is in loadTeam");
    teamLoaded = false;
    _http.post('rest/team/getTeam.json', JSON.encode(ID))
      .then((HttpResponse response) {
        print(response);
        team = new Team.fromJson(response.data);
        teamLoaded = true;
        html.window.console.info("Success on loading team");
      })
      .catchError((e) {
        print(e);
        teamLoaded = false;
        html.window.console.info("Could not load rest/team/getTeam.json");
      });    
  }

  @override
  void attach() {
    html.window.console.info("Constructor: teamId=" + ID.toString());
     loadTeam();
     loadLeagues();
  }
}