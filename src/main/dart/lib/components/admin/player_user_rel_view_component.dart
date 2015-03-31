part of fremad;

@Component(
    selector: 'admin-player-user-rel-view',
    templateUrl: 'packages/fremad/components/admin/player_user_rel_view.html',
    cssUrl: 'packages/fremad/components/admin/player_user_rel_view.css',
    useShadowDom: false
)

class ShowAdminPlayerUserRelComponent {
  final Http _http;
  bool playersLoaded = false;
  bool userMetaLoaded = false;
  bool userPlayerRelLoaded = false;
  bool suggestedUserPlayerRelLoaded = false;  

  bool connectedUserMetaAndPlayerMerged = false;
  bool connectedSuggestedUserMetaAndPlayerMerged = false;

  PlayerList playerListObject;
  UserMetaList userMetaListObject;
  IntValuePairList intValuePairListObject;
  IntValuePairList intValuePairListSuggestedObject;
  
  List<Player> playerList;
  List<UserMeta> userMetaList;
  List<IntValuePair> connectedIdList;
  List<IntValuePair> suggestedIdList;
  
  List<PlayerAndUserMeta> connectedPlayerAndUserMetaList;
  List<PlayerAndUserMeta> suggestedPlayerAndUserMetaList;
  List<Player> unconnectedPlayerList;
  List<UserMeta> unconnectedUserMetaList;
  
  String errorMessage = "";
  
  bool showPlayerList = false;
  bool showUserList = false;
  int selectedUser = -1;
  int selectedPlayer = -1;
  
  
  bool showConnectedPlayerAndUsers = true;
  bool showSuggestedPlayerAndUsers = true;
  bool showUnconnectedPlayers = true;
  bool showUnconnectedUsers = true;
  
  ShowAdminPlayerUserRelComponent(this._http){
    loadPlayerUserRelations();
    loadPlayers();
    loadUserMeta();
    loadPlayerUserRelationsSuggestions();
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
        if(doMergeConnectedPlayerAndUserMeta()){
          mergeConnectedPlayerAndUserMeta();
        }
        if(doMergeSuggestedConnectedPlayerAndUserMeta()){
          mergeSuggestedPlayerAndUserMeta();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        playersLoaded = false;
        html.window.console.info("Could not load rest/player/getPlayers.json");
      });
  } 
  
  void loadUserMeta(){
    html.window.console.info("Is in loadUserMeta");
    userMetaLoaded = false;
    _http.get('rest/user/getUserMetaPlayers.json')
      .then((HttpResponse response) {
        print(response);
        userMetaListObject = new UserMetaList.fromJson(response.data);
        userMetaList = userMetaListObject.userMetaList;
        userMetaLoaded = true;
        html.window.console.info("Success on loading userMeta");
        if(doMergeConnectedPlayerAndUserMeta()){
          mergeConnectedPlayerAndUserMeta();
        }
        if(doMergeSuggestedConnectedPlayerAndUserMeta()){
          mergeSuggestedPlayerAndUserMeta();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        userMetaLoaded = false;
        html.window.console.info("Could not load rest/user/getUserMetaPlayers.json");
      });
  }
  
  
  void loadPlayerUserRelations(){
    html.window.console.info("Is in loadPlayerUserRelations");
    userPlayerRelLoaded = false;
    _http.get('rest/player/getPlayerUserRelations.json')
      .then((HttpResponse response) {
        print(response);
        intValuePairListObject = new IntValuePairList.fromJson(response.data);
        connectedIdList = intValuePairListObject.intValuePairList;
        userPlayerRelLoaded = true;
        html.window.console.info("Success on loading PlayerUserRel");
        if(doMergeConnectedPlayerAndUserMeta()){
          mergeConnectedPlayerAndUserMeta();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        userPlayerRelLoaded = false;
        html.window.console.info("Could not load rest/player/getPlayerUserRelations.json");
      });

  }
  
  void loadPlayerUserRelationsSuggestions(){
    html.window.console.info("Is in loadPlayerUserRelationsSuggestions");
    suggestedUserPlayerRelLoaded = false;
    _http.get('rest/player/getSuggestedPlayerUserRelations.json')
      .then((HttpResponse response) {
        html.window.console.info("Dud0");
        print(response);
        html.window.console.info("Dud1");
        intValuePairListSuggestedObject = new IntValuePairList.fromJson(response.data);
        html.window.console.info("Dud2");
        suggestedIdList = intValuePairListSuggestedObject.intValuePairList;
        html.window.console.info("Dud3");
        suggestedUserPlayerRelLoaded = true;
        html.window.console.info("Success on loading PlayerUserRel");
        if(doMergeSuggestedConnectedPlayerAndUserMeta()){
          mergeSuggestedPlayerAndUserMeta();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        suggestedUserPlayerRelLoaded = false;
        html.window.console.info("Could not load rest/player/getSuggestedPlayerUserRelations.json");
      });

  }
  
  void mergeConnectedPlayerAndUserMeta(){
    html.window.console.info("Is in mergeConnectedPlayerAndUserMeta");
    connectedUserMetaAndPlayerMerged = true;
    connectedPlayerAndUserMetaList = new List<PlayerAndUserMeta>();
    for(IntValuePair connection in connectedIdList){
      addPlayerUserMeta(connection);
    } 
    unconnectedPlayerList = playerList.where(
        (Player) => connectedPlayerAndUserMetaList.every(
            (PlayerAndUserMeta) => PlayerAndUserMeta.player != Player));
    
    unconnectedUserMetaList = userMetaList.where(
        (UserMeta) => connectedPlayerAndUserMetaList.every(
            (PlayerAndUserMeta) => PlayerAndUserMeta.userMeta != UserMeta));

  }
  
  void mergeSuggestedPlayerAndUserMeta(){
    html.window.console.info("Is in mergeSuggestedPlayerAndUserMeta");
    connectedSuggestedUserMetaAndPlayerMerged = true;
    suggestedPlayerAndUserMetaList = new List<PlayerAndUserMeta>();
    for(IntValuePair connection in suggestedIdList){
      addSuggestedPlayerUserMeta(connection);
    }
  }
 
  void deleteRel(PlayerAndUserMeta connection){
    html.window.console.info("Is in deleteRel");
    IntValuePair relation = new IntValuePair(connection.player.id, connection.userMeta.userId);
    _http.post('rest/player/deletePlayerUserRelation.json', JSON.encode(relation))
      .then((HttpResponse response) {
        print(response);
        connectedPlayerAndUserMetaList.removeWhere((PlayerAndUserMeta) => PlayerAndUserMeta.player.id == relation.key);
        html.window.console.info("Success on deleting relation");
        MESSAGE.addSuccessMessage("Relation deleted");
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        html.window.console.info("Could not load rest/player/deletePlayerUserRelation.json");
      });
  }
  
  void addRel(){
    html.window.console.info("Is in addRel");
    IntValuePair relation = new IntValuePair(selectedPlayer, selectedUser);
    _http.post('rest/player/addPlayerUserRelation.json', JSON.encode(relation))
      .then((HttpResponse response) {
        addPlayerUserMeta(relation);
        refreshSuggestion(relation);
        html.window.console.info("Success on adding relation");
        MESSAGE.addSuccessMessage("Relation added");
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        html.window.console.info("Could not load rest/player/addPlayerUserRelation.json");
      });
  }
  
  void selectUser(int userId){
    selectedUser = userId;
    if(selectedPlayer == -1){
      showPlayerList = true;     
    }else{
      addRel();
      cancel();
    }
  }
  
  void selectPlayer(int playerId){
    selectedPlayer = playerId;
    if(selectedUser == -1){
      showUserList = true;     
    }else{
      addRel();
      cancel();
    }
  }
  
  void cancel(){
    showPlayerList = false;
    showUserList = false;
    selectedPlayer = -1;
    selectedUser = -1;
  }
  
  void refreshSuggestion(IntValuePair relation){
    suggestedPlayerAndUserMetaList.removeWhere((PlayerAndUserMeta) => PlayerAndUserMeta.player.id == relation.key);
    suggestedPlayerAndUserMetaList.removeWhere((PlayerAndUserMeta) => PlayerAndUserMeta.userMeta.userId == relation.value);
  }
  
  void addPlayerUserMeta(IntValuePair relation){
    connectedPlayerAndUserMetaList.add(new PlayerAndUserMeta(
              playerList.where((Player) => Player.id == relation.key).first,
              userMetaList.where((UserMeta) => UserMeta.userId == relation.value).first));
  }
  
  void addSuggestedPlayerUserMeta(IntValuePair relation){
    suggestedPlayerAndUserMetaList.add(new PlayerAndUserMeta(
              playerList.where((Player) => Player.id == relation.key).first,
              userMetaList.where((UserMeta) => UserMeta.userId == relation.value).first));
  }
  
  void addSuggestedRel(PlayerAndUserMeta suggestion){
    selectedPlayer = suggestion.player.id;
    selectedUser = suggestion.userMeta.userId;
    addRel();
    cancel();
    
  }
  
  bool doMergeConnectedPlayerAndUserMeta(){
    if(playersLoaded && userMetaLoaded && userPlayerRelLoaded && !connectedUserMetaAndPlayerMerged){
      return true;
    }
    return false;
  }
  
  bool doMergeSuggestedConnectedPlayerAndUserMeta(){
      if(playersLoaded && userMetaLoaded && suggestedUserPlayerRelLoaded && !connectedSuggestedUserMetaAndPlayerMerged){
        return true;
      }
      return false;
    }
  
  
  void changeConnectedPlayerAndUsersFilter(){
    showConnectedPlayerAndUsers = !showConnectedPlayerAndUsers;
  }
  
  void changeSuggestedPlayerAndUsersFilter(){
    showSuggestedPlayerAndUsers = !showSuggestedPlayerAndUsers;
  }
  
  void changeUnconnectedPlayerFilter(){
    showUnconnectedPlayers = !showUnconnectedPlayers;
  }
  
  void changeUnconnectedUsersFilter(){
    showUnconnectedUsers = !showUnconnectedUsers;
  }
  
  String getFilterSymbol(bool show){
    if(show){
      return "hide";
    }
    return "show";
  }
  
}