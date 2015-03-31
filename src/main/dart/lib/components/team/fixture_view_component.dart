part of fremad;

@Component(
    selector: 'team-fixture-view',
    templateUrl: 'packages/fremad/components/team/fixture_view.html',
    cssUrl: 'packages/fremad/components/team/fixture_view.css',
    useShadowDom: false
)
class ShowTeamFixtureComponent {
  
  Http _http;
  int teamID;
  bool fixturesLoaded = false;
  bool leaguesLoaded = false;
  bool teamLoaded = false;
  
  int selectedLeague;
  
  Team team;
  MatchList matchListObject;
  List<MatchEntry> matchEntryList;
  LeagueList leagueListObject;
  List<League> leagueList;
  
  ShowTeamFixtureComponent(this._http, RouteProvider routeProvider){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in fixture found id: " + teamID.toString());
    loadTeam();
    loadLeagues();
    loadFixtures();
  }
  
  void loadFixtures() {
    html.window.console.info("Is in loadLeagues");
    fixturesLoaded = false;
    _http.post('rest/match/getMatches.json', JSON.encode(teamID))
      .then((HttpResponse response) {
        print(response);
        matchListObject = new MatchList.fromJson(response.data);
        matchEntryList = matchListObject.matchEntryList;
        fixturesLoaded = true;
        html.window.console.info("Success on loading fixtures");
      })
      .catchError((e) {
        print(e);
        fixturesLoaded = false;
        html.window.console.info("Could not load rest/match/getMatches.json");
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
        selectedLeague = leagueList.first.id;
        html.window.console.info("Success on loading leagues");
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
  
  bool filter(MatchEntry entry){
    return entry.league == selectedLeague;
  }
  
  void setLeague(int id){
    selectedLeague = id;
  }
  
  String getDateAndTimeString(MatchEntry entry){
    return DateTimeUtils.getDateText(entry.date) + " " + entry.getTimeAsString();
  }
  
  String getHomeTeamName(MatchEntry entry){
    if(entry.homeMatch){
      return team.name;
    }else{
      return entry.opposingTeamName;
    }
  }
  String getAwayTeamName(MatchEntry entry){
    if(!entry.homeMatch){
      return team.name;
    }else{
      return entry.opposingTeamName;
    }
  }
  
  String getHomeTeamScore(MatchEntry entry){
    if(entry.homeMatch){
      return entry.fremadGoals.toString();
    }else{
      return entry.opposingTeamGoals.toString();
    }
  }
  
  String getAwayTeamScore(MatchEntry entry){
    if(!entry.homeMatch){
      return entry.fremadGoals.toString();
    }else{
      return entry.opposingTeamGoals.toString();
    }
  }
  
  bool beenPlayed(MatchEntry entry){
    if(entry.fremadGoals == -1 && entry.opposingTeamGoals == -1){
      return false;
    }
    return true;
  }
}