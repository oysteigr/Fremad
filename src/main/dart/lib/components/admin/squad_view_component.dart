part of fremad;

@Component(
    selector: 'admin-squad-view',
    templateUrl: 'packages/fremad/components/admin/squad_view.html',
    cssUrl: 'packages/fremad/components/admin/squad_view.css',
    useShadowDom: false
)

class ShowAdminSquadComponent {
  final Http _http;
  bool teamsLoaded = false;
  bool playersLoaded = false;
  bool playerNotesLoaded = false;
  bool playerAndNotesMerged = false;

  TeamList teamListObject;
  PlayerList playerListObject;
  PlayerNoteList playerNoteListObject;
  
  List<Team> teamList;
  List<Player> playerList;
  List<PlayerNote> playerNoteList;
  List<PlayerWithNotes> playerWithNotesList;

  int selectedTeam = 1;
  int selectedPlayer = -1;
  String errorMessage = "";
  String playerNote = "";
  bool showAddNote = false;

  
  ShowAdminSquadComponent(this._http){
    loadTeams();
    loadPlayers();
    loadPlayerNotes();
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
        if(playerNotesLoaded && !playerAndNotesMerged){
          mergePlayerAndNotes();
        }
      })
      .catchError((e) {
        print(e);
        playersLoaded = false;
        html.window.console.info("Could not load rest/player/getPlayers.json");
      });
  } 
  
  void loadPlayerNotes(){
    html.window.console.info("Is in loadPlayerNotes");
    playerNotesLoaded = false;
    _http.get('rest/player/getPlayerNotes.json')
      .then((HttpResponse response) {
        print(response);
        playerNoteListObject = new PlayerNoteList.fromJson(response.data);
        playerNoteList = playerNoteListObject.playerNoteList;
        playerNotesLoaded = true;
        html.window.console.info("Success on loading playerNotes");
        if(playersLoaded && !playerAndNotesMerged){
          mergePlayerAndNotes();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        playerNotesLoaded = false;
        html.window.console.info("Could not load rest/player/getPlayerNotes.json");
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
  
  void mergePlayerAndNotes(){
    html.window.console.info("Is in mergePlayerAndNotes");
    playerWithNotesList = new List<PlayerWithNotes>();
    for(Player player in playerList){
      playerWithNotesList.add(new PlayerWithNotes(player, 
          playerNoteList.where((PlayerNote) => PlayerNote.playerId == player.id)));
    }
  }
  
  void deleteNote(int noteId){
    html.window.console.info("Is in addNote");
    PlayerNote playerNoteResponse;
    PlayerNote playerNoteTemp = playerNoteList.where((PlayerNote) => PlayerNote.id == noteId).first;
    int index = playerNoteList.indexOf(playerNoteTemp);
    _http.post('rest/player/deletePlayerNote.json', playerNoteTemp)
      .then((HttpResponse response) {
        print(response);
        playerNoteResponse = new PlayerNote.fromJson(response.data);
        playerNoteList.removeAt(index);
        html.window.console.info("Success on adding playerNote");
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        html.window.console.info("Could not load rest/player/deletePlayerNote.json");
      });
  }
  
  void setShowAddNote(int playerId){
    html.window.console.info("Is in setShowAddNote with playerId = " + playerId.toString());
    showAddNote = true;
    selectedPlayer = playerId;
  }
  
  void addNote(){
    html.window.console.info("Is in addNote");
    html.window.console.info(playerWithNotesList);
    PlayerNote playerNoteTemp = new PlayerNote(-1, selectedPlayer, playerNote, new DateTime.now());
    _http.post('rest/player/addPlayerNote.json', playerNoteTemp)
      .then((HttpResponse response) {
        print(response);
        playerNoteTemp = new PlayerNote.fromJson(response.data);
        playerNoteList.add(playerNoteTemp);
        html.window.console.info("Success on adding playerNote");
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        html.window.console.info("Could not load rest/player/addPlayerNote.json");
      });
    
    cancel();
  }
  
  void cancel(){
    html.window.console.info("Is in cancel");
    showAddNote = false;
    selectedPlayer = -1;
    playerNote = "";
  }
  
  String getSelectedPlayerName(){
    if(selectedPlayer == -1){
      return "";
    }
    return playerWithNotesList.where((PlayerWithNotes) => PlayerWithNotes.player.id == selectedPlayer).first.player.getNameString();
    
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