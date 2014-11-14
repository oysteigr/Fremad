part of fremad;

@Component(
    selector: 'team-fixture-view',
    templateUrl: 'packages/fremad/components/team/fixture_view.html',
    useShadowDom: false
)
class ShowTeamFixtureComponent {
  
  Http _http;
  int teamID;
  bool tableLoaded = false;
  MatchList matchListObject;
  List<MatchEntry> matchEntryList;
  LeagueList leagueListObject;
  List<League> leagueList;
  
  ShowTeamFixtureComponent(this._http, RouteProvider routeProvider){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in fixture found id: " + teamID.toString());
    loadLeagues();
  }
  
  void loadFixtures() {
    html.window.console.info("Is in loadLeagues");
    tableLoaded = false;
    _http.post('rest/match/getMatches.json', JSON.encode(leagueList.first.getID))
      .then((HttpResponse response) {
        print(response);
        matchListObject = new MatchList.fromJson(response.data);
        matchEntryList = matchListObject.matchEntryList;
        tableLoaded = true;
        html.window.console.info("Success on loading fixtures");
      })
      .catchError((e) {
        print(e);
        tableLoaded = false;
        html.window.console.info("Could not load rest/league/getLeagues.json");
      });
  } 
  void loadLeagues() {
    html.window.console.info("Is in loadLeagues");
    tableLoaded = false;
    _http.post('rest/league/getLeaguesByTeam.json', JSON.encode(teamID))
      .then((HttpResponse response) {
        print(response);
        leagueListObject = new LeagueList.fromJson(response.data);
        leagueList = leagueListObject.leagueList;
        tableLoaded = true;
        html.window.console.info("Success on loading leagues");
        loadFixtures();
      })
      .catchError((e) {
        print(e);
        tableLoaded = false;
        html.window.console.info("Could not load rest/league/getLeagues.json");
      });
  } 
}