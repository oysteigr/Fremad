part of fremad;

@Component(
    selector: 'main-menu',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/main_menu.html',
    useShadowDom: false
)
class MainMenuComponent {
  final Http _http;
  bool teamsLoaded = false;
  bool showLogin = false;
  bool showSignup = false;
  bool registerSuccess = false;
  bool loginSuccess = false;
  List<Team> teamList;
  TeamList teamListObject;
  User currentUser;
  UserLogon userLogon;
  
  MainMenuComponent(this._http){
    getUserRole();
    loadTeams();
  }
  void getUserRole(){
    html.window.console.info("Is in getUserRole");
    int role = 0; 
    
    _http.get('rest/user/getUserRole.json')
    .then((HttpResponse response) {
      print(response);
      html.window.console.info(response.toString());
      role = int.parse(response.data.toString());
      if(role > 0){
        loginSuccess = true;
        html.window.console.info("user is loged in");
        USER.setUserRole(role);
        showLogin = false;
      } else{
        loginSuccess = false;
        html.window.console.info("user is not log in");
      }
    })
    .catchError((e) {
      print(e);
      loginSuccess = false;
      html.window.console.info("Could not load rest/user/loginUser.json");
    });
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
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        teamsLoaded = false;
        html.window.console.info("Could not load rest/team/getTeams.json");
      });
  }
  
  void setShowLogin(bool show){
    DateTime now = new DateTime.now();
    userLogon = new UserLogon("", "");
    showLogin = show;
  }
  
  void setShowSignup(bool show){
    DateTime now = new DateTime.now();
    currentUser = new User(-1, "", "", "", 1, null, false);
    showLogin = false;
    showSignup = show;
  }
  
  void registerNewUser(){
    html.window.console.info("Is in registerNewUser");
    
    _http.post('rest/user/createUser.json', JSON.encode(currentUser))
      .then((HttpResponse response) {
        print(response);
        registerSuccess = response.data.toString == "true";
        html.window.console.info("registerNewUser: " + registerSuccess.toString());
      })
      .catchError((e) {
        print(e);
        registerSuccess = false;
        html.window.console.info("Could not load rest/user/createUser.json");
      });

  } 
  
  void loginUser(){
    html.window.console.info("Is in loginUser");
    int role;
    
    _http.post('rest/user/loginUser.json', JSON.encode(userLogon))
      .then((HttpResponse response) {
        print(response);
        html.window.console.info(response.toString());
        role = int.parse(response.data.toString());
        if(role > 0){
          loginSuccess = true;
          html.window.console.info("user loged in: " + userLogon.userName);
          USER.setUserRole(role);
          showLogin = false;
        } else{
          loginSuccess = false;
          html.window.console.info("user could not log in: " + userLogon.userName);
        }
      })
      .catchError((e) {
        print(e);
        loginSuccess = false;
        html.window.console.info("Could not load rest/user/loginUser.json");
      });

  } 
  
  void logoutUser(){
    html.window.console.info("Is in logoutUser");
    bool success;
    
    _http.get('rest/user/logoutUser.json')
      .then((HttpResponse response) {
        print(response);
        html.window.console.info(response.toString());
        success = response.data.toString() == 'true';
        if(success){
          html.window.console.info("user loged out");
          USER.setUserRole(0);
        } else{
          html.window.console.info("could not log out");
        }
      })
      .catchError((e) {
        print(e);
        loginSuccess = false;
        html.window.console.info("Could not load rest/user/logoutUser.json");
      });    
  }
  
  bool showBasedOnRole(int role){
    return USER.checkUserRole(role);
  }
}