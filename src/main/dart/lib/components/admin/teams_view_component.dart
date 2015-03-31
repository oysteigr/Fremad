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
      bool isValidated = true;
      
      
  ShowAdminTeamsComponent(this._http){
    loadData();
  }
  
  void loadData() {
    html.window.console.info("Is in loadData");
    tableLoaded = false;
    _http.get('rest/team/getTeams.json')
      .then((HttpResponse response) {
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
  
  void addTeam(){
    Team team = new Team(-1, name, onlineId);
    _http.post('rest/team/addTeam.json', JSON.encode(team))
    .then((HttpResponse response) {
      teamList.add(new Team.fromJson(response.data));
      name = '';
      onlineId = 0;
      isEditing = false;
      MESSAGE.addSuccessMessage("Team added");
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/team/addTeam.json");
    });
    html.window.console.info("Added team: " + name + " succeded!");
  }
  
  void updateTeam(int id, String name, int onlineId){
    html.window.console.info("In update()");
    int index = teamList.indexOf(teamList.where((Team) => Team.id == selectedTeam).first);

    _http.post('rest/team/updateTeam.json', JSON.encode(teamList.elementAt(index)))
    .then((HttpResponse response) {
      MESSAGE.addSuccessMessage("Team updated");
    })
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
    isValidated = true;
    html.window.console.info("Cancel");
  }
  
  void delete(int id, String name, int onlineId){
    html.window.console.info("In delete()");   
    int index = teamList.indexOf(teamList.where((Team) => Team.id == selectedTeam).first);

    _http.post('rest/team/deleteTeam.json', JSON.encode(teamList.elementAt(index)))
    .then((HttpResponse response) {
      MESSAGE.addSuccessMessage("Team deleted");
      teamList.removeAt(index);
    })
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
    isValidated = true;
    isEditing = false;
  }
  
  void getName(id){
    html.window.console.info("In getName");
    _http.post('rest/team/getNameFromId', JSON.encode(id))
    .then((HttpResponse response) {
      if(isEditing){
        html.window.console.info("getName by adding team ");
        name = response.data.toString();
     }else{
       html.window.console.info("getName by selected team ");
       teamList.where((Team) => Team.id == selectedTeam).first.name = response.data.toString();
      }
      if (response.data.toString() == "ID_ERROR"){
        isValidated = false;
      } else {
        isValidated = true;
      }
    });
    
    
  }
  
  void setEditingMode(){
    isEditing = true;
    isValidated = false;
    selectedTeam = -1;
  }
  
  bool isActive(int id){
    return selectedTeam != id;
  }

  void idChanged(){
    html.window.console.info("In idChanged");
    isValidated = false;
  }
}