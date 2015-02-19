part of fremad;

@Component(
    selector: 'admin-user-login-view',
    templateUrl: 'packages/fremad/components/admin/user_login_view.html',
    cssUrl: 'packages/fremad/components/admin/user_login_view.css',
    useShadowDom: false
)
class ShowAdminUserLoginComponent {
  final Http _http;
  bool loginsLoaded = false;
  bool userMetaLoaded = false;
  bool userMetaAndLoginLoaded = false;
  
  String errorMessage = "";
  
  UserMetaList userMetaListObject;
  UserLoginLogList userLoginLogListObject;
  
  List<UserMeta> userMetaList;
  List<UserLoginLog> userLoginLogList;
  List<UserMetaAndLogin> userMetaAndLoginList;
  
  int selectedUser = -1;
  
  
  
  ShowAdminUserLoginComponent(this._http){
    loadLogins();
    loadUsersMeta();

 //   leagueList.add(new League(2166, 2013, 0));
  }
  
  void loadLogins() {
    html.window.console.info("Is in loadUsers");
    loginsLoaded = false;
    _http.get('rest/user/getUserLogins.json')
      .then((HttpResponse response) {
        print(response);
        userLoginLogListObject = new UserLoginLogList.fromJson(response.data);
        userLoginLogList = userLoginLogListObject.userLoginLogList;
        loginsLoaded = true;
        html.window.console.info("Success on loading logins");
        if(userMetaLoaded && !userMetaAndLoginLoaded){
          mergeMetaAndLogins();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        loginsLoaded = false;
        html.window.console.info("Could not load rest/user/getUserLogins.json");
      });
  } 
  
  void loadUsersMeta() {
    html.window.console.info("Is in getUsersMeta");
    userMetaLoaded = false;
    _http.get('rest/user/getUsersMeta.json')
      .then((HttpResponse response) {
        print(response);
        userMetaListObject = new UserMetaList.fromJson(response.data);
        userMetaList = userMetaListObject.userMetaList;
        userMetaLoaded = true;
        html.window.console.info("Success on loading user meta");
        if(loginsLoaded && !userMetaAndLoginLoaded){
          mergeMetaAndLogins();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        userMetaLoaded = false;
        html.window.console.info("Could not load rest/team/getUsersMeta.json");
      });
  } 
  
  void mergeMetaAndLogins(){
    userMetaAndLoginList = new List<UserMetaAndLogin>();
    for(UserMeta userMeta in userMetaList){
      userMetaAndLoginList.add(new UserMetaAndLogin(userMeta,
          userLoginLogList.where((UserLoginLog) => UserLoginLog.userId == userMeta.userId)));
    }
    userMetaAndLoginList.removeWhere((UserMetaAndLogin) => UserMetaAndLogin.loginList.isEmpty);
    userMetaAndLoginList.sort((y, x) => x.loginList.last.date.compareTo(y.loginList.last.date));
    userMetaAndLoginLoaded = true;
  }
  
  String getElapedTime(UserMetaAndLogin userMetaAndLogin){
    return DateTimeUtils.elapedTime(userMetaAndLogin.loginList.last.date);
  }
}