part of fremad;

@Component(
    selector: 'admin-squad-view',
    templateUrl: 'packages/fremad/components/admin/squad_view.html',
    cssUrl: 'packages/fremad/components/admin/squad_view.css',
    useShadowDom: false
)

class ShowAdminSquadComponent {
  final Http _http;
  bool teamsLoaded;
  bool playersLoaded;
  TeamList teamListObject;
  List<Team> teamList;
  PlayerList playerListObject;
  List<Player> playerList;
  int selectedTeam = 1;

  
  ShowAdminSquadComponent(this._http){
    loadTeams();
    loadPlayers();
  }
  
  void loadTeams() {
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
  
  void loadPlayers() {
    html.window.console.info("Is in loadPlayers");
    playersLoaded = false;
    _http.get('rest/player/getPlayers.json')
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
  
  
  void updatePlayer(int playerId){
    int index =  playerList.indexOf(playerList.where((Player) => Player.id == playerId).first);
    playerList.elementAt(index).active = !playerList.elementAt(index).active;
    _http.post('rest/player/updatePlayer.json', JSON.encode(playerList.elementAt(index)))
    .then((HttpResponse response) {

    })
    .catchError((e) {
      playerList.elementAt(index).active = !playerList.elementAt(index).active;
      print(e);
      html.window.console.info("Could not load rest/player/addPlayer.json");
    });

  }
  
  bool filter(Player player, bool active){
    if(selectedTeam == player.team && active == player.active){
      return true;
    }
    return false;
  }
  
  void setTeam(int teamId){
    this.selectedTeam = teamId;
  }
  
}