part of fremad;

@Component(
    selector: 'team-table-view',
    templateUrl: 'packages/fremad/components/team/table_view.html',
    cssUrl: 'packages/fremad/components/team/table_view.css',
    useShadowDom: false
)
class ShowTeamTableComponent {
  final Http _http;
  bool tableEntriesLoaded = false;
  bool leaguesLoaded = false;
  bool teamLoaded = false;
  String message;
  int teamID;
  Team team;
  TableEntryList tableEntryListObject;
  List<TableEntry> tableEntryList;
  LeagueList leagueListObject;
  List<League> leagueList;
  
  ShowTeamTableComponent(this._http, RouteProvider routeProvider){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in table found id: " + teamID.toString());
    loadTeam();
    loadLeagues();
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
        html.window.console.info("Success on loading fixtures");
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
    _http.post('rest/league/getLeaguesByTeam.json', JSON.encode(teamID))
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
    _http.post('rest/team/getTeam.json', JSON.encode(teamID))
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
  
  String getDiff(TableEntry entry){
    return (entry.goalsScored - entry.goalsConceded).toString();
  }

}