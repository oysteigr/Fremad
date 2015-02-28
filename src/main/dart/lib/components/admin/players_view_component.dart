part of fremad;

@Component(
    selector: 'admin-players-view',
    templateUrl: 'packages/fremad/components/admin/players_view.html',
    cssUrl: 'packages/fremad/components/admin/players_view.css',
    useShadowDom: false
)

class ShowAdminPlayersComponent {
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
  Player currentPlayer = new Player(-1, "", "", 0, 0, "", "", -1, false);
  int selectedTeam = 9;
  
  ShowAdminPlayersComponent(this._http){
    initPlayer();
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
  
  void addPlayer(){
    _http.post('rest/player/addPlayer.json', JSON.encode(currentPlayer))
    .then((HttpResponse response) {
      playerList.add(new Player.fromJson(response.data));
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/player/addPlayer.json");
    });
    isEditing = false;
    initPlayer();
  }
  
  void updatePlayer(){
    _http.post('rest/player/updatePlayer.json', JSON.encode(currentPlayer))
    .then((HttpResponse response) {
      playerList.add(new Player.fromJson(response.data));
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/player/addPlayer.json");
    });
    isEditing = false;
    initPlayer();
  }
  
  void deletePlayer(){
    int index = playerList.indexOf(playerList.where((Player) => Player.id == selectedPlayer).first);
    _http.post('rest/player/deletePlayer.json', JSON.encode(playerList.elementAt(index)))
     .then((HttpResponse response) {
        Player playerResponse = new Player.fromJson(response.data);
        assert(playerResponse.id == playerList.where((Player) => Player.id == selectedPlayer).first.id);
        assert(playerResponse.number == playerList.where((Player) => Player.id == selectedPlayer).first.number);
        assert(playerResponse.team == playerList.where((Player) => Player.id == selectedPlayer).first.team);
        playerList.removeAt(index);
     })
     .catchError((e) {
       print(e);
       html.window.console.info("Could not load rest/player/addPlayer.json");
     });
    
   }
  
  void setEditingMode(int id){
    html.window.console.info("Is in setEditingMode");
    int index = playerList.indexOf(playerList.where((Player) => Player.id == selectedPlayer).first);
    currentPlayer = playerList.elementAt(index);
    isEditing = true;
    isAdding = false;
    selectedPlayer = -1;
  }
  
  void setAddingMode(){
    html.window.console.info("Is in setEditingMode");
    isEditing = true;
    isAdding = true;
    selectedPlayer = -1;
  }
  
  void selectPlayer(int id){
    if(selectedPlayer == id){
      selectedPlayer = -1;
    }else{
      selectedPlayer = id;
    }
  }
  
  bool isActive(int id){
    return id == selectedPlayer;
  }
  
  void cancel(){
    isEditing = false;
    initPlayer();
  }
  
  void initPlayer(){
    html.window.console.info("Is in initPlayer");
    currentPlayer = new Player(-1, "", "", 0, 0, "", "", -1, false);
  }
  
  bool filter(Player player){
    if(selectedTeam == player.team || selectedTeam == 9){
      return true;
    }
    return false;
  }
  
  void setTeam(int teamId){
    this.selectedTeam = teamId;
  }
}