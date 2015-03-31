part of fremad;

@Component(
    selector: 'admin-events-view',
    templateUrl: 'packages/fremad/components/admin/events_view.html',
    cssUrl: 'packages/fremad/components/admin/events_view.css',
    useShadowDom: false
)
class ShowAdminEventsComponent {
  void create() {
    html.window.console.info("Is in ShowAdminEventsComponent");
  }

  final Http _http;
  bool teamsLoaded;
  bool matchesLoaded;
  bool isValidated = true;

  List<Team> teamList;
  List<MatchEntry> matchList;
  
  int selectedTeam;
  int selectedMatch;
  
  ShowAdminEventsComponent(this._http){
    selectedTeam = 9;
    loadTeams();
    loadMatches();
  }

  void loadTeams() {
      html.window.console.info("Is in loadTeams");
      teamsLoaded = false;
      _http.get('rest/team/getTeams.json')
        .then((HttpResponse response) {
          print(response);
          TeamList teamListObject = new TeamList.fromJson(response.data);
          teamList = teamListObject.teamList;
          teamsLoaded = true;
          html.window.console.info("Success on loading table");
        })
        .catchError((e) {
          print(e);
          teamsLoaded = false;
          html.window.console.info("Could not load rest/team/getTeams.json");
        });
  } 
  
  void loadMatches() {
      html.window.console.info("Is in loadMatches");
      matchesLoaded = false;
      _http.get('rest/match/getThisYearsMatches.json')
        .then((HttpResponse response) {
          print(response);
          MatchList matchListObject = new MatchList.fromJson(response.data);
          matchList = matchListObject.matchEntryList;
          matchesLoaded = true;
          html.window.console.info("Success on loading table");
        })
        .catchError((e) {
          print(e);
          matchesLoaded = false;
          html.window.console.info("Could not load rest/match/getMathes.json");
        });
  }
  
  bool filter(MatchEntry match){
    if (selectedTeam == 9 || selectedTeam == match.fremadTeam) {
      return true;
    }
    return false;
  }
    
  bool isActive(int id){
    return id == selectedTeam;
  }

  void setTeam(int id) {
    selectedTeam = id;
  }
  
  String getHomeTeamName(MatchEntry match) {
    if (match.homeMatch) {
      return teamList.where((Team) => Team.id == match.fremadTeam).first.name;
    } else {
      return match.opposingTeamName;
    }
  }
  
  String getAwayTeamName(MatchEntry match) {
    if (! match.homeMatch) {
      return teamList.where((Team) => Team.id == match.fremadTeam).first.name;
    } else {
      return match.opposingTeamName;
    }
  }
  
  void selectMatch(MatchEntry match) {
    selectedMatch = match.id;
    html.window.console.info("Selected match " + match.opposingTeamName);
  }
}