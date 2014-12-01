part of fremad;

@Component(
    selector: 'team-players-view',
    templateUrl: 'packages/fremad/components/team/players_view.html',
    cssUrl: 'packages/fremad/components/team/players_view.css',
    useShadowDom: false
)
class ShowTeamPlayersComponent {
  final Http _http;
  int teamID;
  bool teamLoaded = false;
  bool playersLoaded = false;
  Team team;
  PlayerList playerListObject;
  List<Player> playerList;
  
  
  ShowTeamPlayersComponent(RouteProvider routeProvider, this._http){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in players found id: " + teamID.toString());
    loadTeam();
    loadPlayers();
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
  
  void loadPlayers() {
    html.window.console.info("Is in loadPlayers");
    playersLoaded = false;
    _http.post('rest/player/getPlayersByTeam.json', JSON.encode(teamID))
      .then((HttpResponse response) {
        print(response);
        playerListObject = new PlayerList.fromJson(response.data);
        playerList = playerListObject.playerList;
        playersLoaded = true;
        html.window.console.info("Success on loading players");
      })
      .catchError((e) {
        print(e);
        playersLoaded = false;
        html.window.console.info("Could not load rest/player/getPlayers.json");
      });
  } 
}