part of fremad;

    @Component(
        selector: 'admin-teams-view',
        templateUrl: 'packages/fremad/components/admin/teams_view.html',
        cssUrl: 'packages/fremad/components/admin/teams_view.css',
        useShadowDom: false
    )
    class ShowAdminTeamsComponent {
      final Http _http;
      bool tableLoaded;
      String name = '';
      int onlineId = 0;
      TeamList teamListObject;
      List<Team> teamList;
      int selectedTeam = -1;
      bool isEditing = false;
      
      
  ShowAdminTeamsComponent(this._http){
    loadData();
  }
  
  void loadData() {
    html.window.console.info("Is in loadData");
    tableLoaded = false;
    _http.get('rest/team/getTeams.json')
      .then((HttpResponse response) {
        print(response);
        teamListObject = new TeamList.fromJson(response.data);
        teamList = teamListObject.teamList;
        tableLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        tableLoaded = false;
        html.window.console.info("Could not load rest/team/getTeams.json");
      });
  } 
  
  void add(){
    Team team = new Team(-1, name, onlineId);
    _http.post('rest/team/addTeam.json', JSON.encode(team))
    .then((HttpResponse response) {
      teamList.add(new Team.fromJson(response.data));
      name = '';
      onlineId = 0;
      isEditing = false;
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/team/addTeam.json");
    });
    html.window.console.info("Added team: " + name + " succeded!");
  }
  
  void update(int id, String name, int onlineId){
    html.window.console.info("In update()");
    Team tempTeam = new Team(id, name, onlineId);
/*   for(int i = 0; i < teamList.length; i++){
      html.window.console.info("For in updateTeam size=" + teamList.length.toString());
      if(teamList.elementAt(i).id == selectedTeam){
        html.window.console.info("For in updateTeam2");
        tempTeam = teamList.elementAt(i);
      }
    }*/
    html.window.console.info("After for in updateTeam");
    html.window.console.info("Where in updateTeam: " + tempTeam.name + " succeded!");
    _http.post('rest/team/updateTeam.json', JSON.encode(tempTeam))
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/team/updateTeam.json");
    });
    selectedTeam = -1;
    html.window.console.info("Updating team: " + name + " succeded!");
  }
  
  void cancel(){
    name = '';
    onlineId = 0;
    isEditing = false;
    html.window.console.info("Cancel");
  }
  
  void delete(int id, String name, int onlineId){
    html.window.console.info("In delete()");
    Team tempTeam = new Team(id, name, onlineId);
    html.window.console.info("Before");
    Team team1 = teamList.firstWhere((Team team) => onlineId == team.id);
    html.window.console.info("After");
 /*   for(int i = 0; i < teamList.length; i++){
      if(teamList.elementAt(i).id == selectedTeam){
        tempTeam = teamList.elementAt(i);
      }
    }*/
    _http.post('rest/team/deleteTeam.json', JSON.encode(tempTeam))
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/team/deleteTeam.json");
    });
    selectedTeam = -1;
    html.window.console.info("Deleting team: " + name + " succeded!");
  }
  
  void selectTeam(int id){
    html.window.console.info("Selected team: " + id.toString());
    if(selectedTeam == id){
      selectedTeam = -1;
    } else {
      selectedTeam = id;
    }
  }
  
  void setEditingMode(){
    isEditing = !isEditing;
  }
  
  bool isActive(int id){
    return selectedTeam != id;
  }
  
  bool isAdding(){
    return !isEditing;
  }
}