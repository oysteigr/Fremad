part of fremad;

@Component(
    selector: 'main-menu',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/main_menu.html',
    cssUrl: 'packages/fremad/components/main_menu.css',
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
  
  UserLogon userLogon;

  bool hideMenuMobile = true;


  
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
        getUserId();
        showLogin = false;
      } else{
        loginSuccess = false;
        html.window.console.info("user is not logged in");
      }
    })
    .catchError((e) {
      print(e);
      loginSuccess = false;
      html.window.console.info("Could not load rest/user/getUserRole.json");
    });
  }
  
  void getUserId(){
    html.window.console.info("Is in getUserId");
    int id = -1; 
    
    _http.get('rest/user/getUserId.json')
    .then((HttpResponse response) {
      print(response);
      html.window.console.info(response.toString());
      id = int.parse(response.data.toString());
      if(id > 0){
        loginSuccess = true;
        html.window.console.info("userId found: " + id.toString());
        USER.setUserId(id);
        showLogin = false;
      } else{
        loginSuccess = false;
        html.window.console.info("userId not found");
      }
    })
    .catchError((e) {
      print(e);
      loginSuccess = false;
      html.window.console.info("Could not load rest/user/getUserId.json");
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
    hideMenuMobile = true;
    DateTime now = new DateTime.now();
    userLogon = new UserLogon("", "");
    showLogin = show;
    
  }
  
  void setShowSignup(bool show){
    DateTime now = new DateTime.now();
    currentUser = new User(-1, "", "", "", 1, new DateTime.now(), false);
    userMeta = new UserMeta(-1, "", "", "", new DateTime.fromMillisecondsSinceEpoch(1), "", "");
    showLogin = false;
    showSignup = show;
  }
  
  
  //Signup in data
  User currentUser;
  UserMeta userMeta;
  String repeatPassword = "";
  String repeatEmail = "";
  String takenUserName = "";
  bool showErrors = false;
  bool userNameTaken = false;
  
  String errorMessage = "";
  
  void registerNewUser(){
    html.window.console.info("Is in registerNewUser");
    int id;
    if(!verifyAll()){
      showErrors = true;
      html.window.console.info("Validation failed");
      return;
    }
    
    _http.post('rest/user/createUser.json', JSON.encode(currentUser))
      .then((HttpResponse response) {
        print(response);
        html.window.console.info(response.data.toString());
        id = int.parse(response.data.toString());
        registerSuccess = id > 0;
        html.window.console.info("registerNewUser: " + registerSuccess.toString());
        if(!registerSuccess){
          userNameTaken = true;
          takenUserName = currentUser.userName;
        } else{
          currentUser.id = id;
          userMeta.userId = id;
          if(!registerUserMeta()){
            html.window.console.info("User created, but could not add userMeta");
          }
          showSignup = false;
        }
      })
      .catchError((e) {
        print(e);
        registerSuccess = false;
        html.window.console.info("Could not load rest/user/createUser.json");
      });

  } 
  
  bool registerUserMeta(){
    html.window.console.info("Is in registerUserMeta");
    _http.post('rest/user/addUserMeta.json', JSON.encode(userMeta))
      .then((HttpResponse response) {
        print(response);
        if(response.status == 204){
          html.window.console.info("Could not register meta");
          return false;
        }
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/user/addUserMeta.json");
        return false;
      });
    return true; 
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
          getUserId();
          showLogin = false;
        } else{
          loginSuccess = false;
          html.window.console.info("user could not log in: " + userLogon.userName);
        }
        errorMessage = "";
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        html.window.console.info(response);
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

  
  bool verifyFirstName(){
    if(currentUser == null){
      return false;
    }
    if(currentUser == ""){
      return false;
    }
    if(userMeta.firstName.length < 3){
      return false;
    }
    return true;
  }
  
  bool verifyLastName(){
    if(currentUser == null){
      return false;
    }
    if(currentUser.userName == ""){
      return false;
    }
    if(userMeta.lastName.split(" ").length != 1){
      return false;
    }
    return true;
  }
  
  bool verifyEmail(){
    if(currentUser == null){
      return false;
    }
    if(currentUser.userName == ""){
      return false;
    }
    if(takenUserName != currentUser.userName){
      userNameTaken = false;
    }
    String p = r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$';

    RegExp regExp = new RegExp(p);

    return regExp.hasMatch(currentUser.userName);
  }
  
  bool verifyRepeatedEmail(){
    if(currentUser == null){
      return false;
    }
    if(repeatEmail == ""){
      return false;
    }
    return repeatEmail == currentUser.userName;
  }
  
  bool verifyPassword(){
    if(currentUser == null){
      return false;
    }
    if(currentUser.password == ""){
      return false;
    }
    if(currentUser.password.length < 7){
      return false;
    }

    return true;
  }
  
  bool verifyRepeatedPassword(){
    if(currentUser == null){
      return false;
    }
    if(repeatPassword == ""){
      return false;
    }
    return repeatPassword == currentUser.password;
  }
  
  bool verifyAll(){
    return verifyFirstName() && 
        verifyLastName() &&
        verifyEmail() &&
        verifyRepeatedEmail() &&
        verifyPassword() &&
        verifyRepeatedPassword();
  }
  
  //Forgot password
  
  bool showForgotPassword = false;
  String forgotErrorMessage = "";
  String userForgotPassword = "";
  
  void forgotPassword(){
    showForgotPassword = true;
    showLogin = false;
  }
  
  void cancelForgot(){
    showForgotPassword = false;
  }
  
  void sendNewPassword(){
    html.window.console.info("Is in sendNewPassword");
    
    _http.post('rest/user/forgotPassword.json', userForgotPassword)
      .then((HttpResponse response) {
        print(response);
        html.window.console.info(response.toString());
        showForgotPassword = false;
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          forgotErrorMessage = response.data.toString();
        }
        html.window.console.info(response);
        html.window.console.info("Could not load rest/user/forgotPassword.json");
      }); 
  }
  
  String getMobileClass(){
    return hideMenuMobile ? "hiding" : "showing";
  }
}