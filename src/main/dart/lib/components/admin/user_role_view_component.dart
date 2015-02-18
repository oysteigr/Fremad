part of fremad;

@Component(
    selector: 'admin-user-role-view',
    templateUrl: 'packages/fremad/components/admin/user_role_view.html',
    cssUrl: 'packages/fremad/components/admin/user_role_view.css',
    useShadowDom: false
)
class ShowAdminUserRoleComponent {
  final Http _http;
  bool usersLoaded = false;
  bool userMetaLoaded = false;
  bool userAndMetaMerged = false;
  
  String errorMessage = "";
  
  UserMetaList userMetaListObject;
  List<UserMeta> userMetaList;
  UserList userListObject;
  List<User> userList;
  List<UserAndMeta> userAndMetaList;
  
  List<bool> filterList;
  int roleFilter = 9;
  
  
  ShowAdminUserRoleComponent(this._http){
    initFilter();
    loadUsers();
    loadUsersMeta();

 //   leagueList.add(new League(2166, 2013, 0));
  }
  
  void loadUsers() {
    html.window.console.info("Is in loadUsers");
    usersLoaded = false;
    _http.get('rest/user/getUsers.json')
      .then((HttpResponse response) {
        print(response);
        userListObject = new UserList.fromJson(response.data);
        userList = userListObject.userList;
        usersLoaded = true;
        html.window.console.info("Success on loading users");
        if(userMetaLoaded && !userAndMetaMerged){
          mergeUserAndMeta();
        }
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        usersLoaded = false;
        html.window.console.info("Could not load rest/user/getUsers.json");
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
        if(usersLoaded && !userAndMetaMerged){
          mergeUserAndMeta();
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
  
  void mergeUserAndMeta(){
    userAndMetaList = new List<UserAndMeta>();
    for(int i = 0 ; i < userList.length ; i++){
      if(userMetaList.where((UserMeta) => UserMeta.userId == userList.elementAt(i).id).length == 1){
        userAndMetaList.add(new UserAndMeta(userList.elementAt(i), userMetaList.where((UserMeta) => UserMeta.userId == userList.elementAt(i).id).first));
      }else{
        userAndMetaList.add(new UserAndMeta(userList.elementAt(i), new UserMeta(userList.elementAt(i).id, "John", "Doe", "", new DateTime(1), "", "")));
      }
      userAndMetaList.sort((x, y) => x.userMeta.getFullName() .compareTo(y.userMeta.getFullName()));
      userAndMetaMerged = true;
    }
  }
  
  bool updateUserRole(int index) {
    html.window.console.info("Is in getUsersMeta");
    _http.post('rest/user/updateUserRole.json', userAndMetaList.elementAt(index).user)
      .then((HttpResponse response) {
        print(response);
        html.window.console.info("Success on updateUserRole");
        return true;
      })
      .catchError((HttpResponse response) {
        html.window.console.info("Could not load rest/team/updateUserRole.json");
        return false;
      });
    return false;
  } 
  
  void changeUp(int userId){
    int index =  userAndMetaList.indexOf(userAndMetaList.where((UserAndMeta) => UserAndMeta.user.id == userId).first);
    userAndMetaList.elementAt(index).user.role++;
    html.window.console.info("Is in changeUp");
    _http.post('rest/user/updateUserRole.json', userAndMetaList.elementAt(index).user)
      .then((HttpResponse response) {
        print(response);
        html.window.console.info("Success on changeUp");
        return true;
      })
      .catchError((HttpResponse response) {
        html.window.console.info("Could not load rest/team/updateUserRole.json");
        userAndMetaList.elementAt(index).user.role--;
      });
  }
  
  void changeDown(int userId){
    int index =  userAndMetaList.indexOf(userAndMetaList.where((UserAndMeta) => UserAndMeta.user.id == userId).first);
    userAndMetaList.elementAt(index).user.role--;
    html.window.console.info("Is in changeUp");
    _http.post('rest/user/updateUserRole.json', userAndMetaList.elementAt(index).user)
      .then((HttpResponse response) {
        print(response);
        html.window.console.info("Success on changeUp");
        return true;
      })
      .catchError((HttpResponse response) {
        html.window.console.info("Could not load rest/team/updateUserRole.json");
        userAndMetaList.elementAt(index).user.role++;
      });
  }
  
  void initFilter(){
    filterList = new List<bool>();
    for(int i = 0; i < 6; i++){
      filterList.add(true);
    }
  }
  
  void changeFilter(int role){ 
    filterList[role-1] = !filterList[role-1];
  }
  
  String getFilterSymbol(int role){
    if(filterList[role-1]){
      return "hide";
    }
    return "show";
  }
  
  bool filterByRole(User user, int role){
    if(user.role == role && user.validated && filterList[role-1]){
      return true;
    }
    return false;
  }
 
  String getRoleFromUser(User user){
    if(!user.validated){
      return "NOT VALIDATED";
    }
    return User.parseRole(user.role);
  }
  
  int getNumberInRole(int role){
    return userAndMetaList.where((UserAndMeta) => UserAndMeta.user.role == role && UserAndMeta.user.validated).length;
  }
  
  dynamic myEncode(dynamic item) {
    if(item is DateTime) {
      return item.toIso8601String();
    }
    return item;
  }
  
}
