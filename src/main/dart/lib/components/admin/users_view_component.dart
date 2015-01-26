part of fremad;

@Component(
    selector: 'admin-users-view',
    templateUrl: 'packages/fremad/components/admin/users_view.html',
    cssUrl: 'packages/fremad/components/admin/users_view.css',
    useShadowDom: false
)
class ShowAdminUsersComponent {
  final Http _http;
  bool usersLoaded;
  bool userMetaLoaded;
  
  bool confirmDelete = false;
  
  UserMetaList userMetaListObject;
  List<UserMeta> userMetaList;
  UserList userListObject;
  List<User> userList;
  int selectedUser = -1;
  
  
  ShowAdminUsersComponent(this._http){
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
      })
      .catchError((e) {
        print(e);
        usersLoaded = false;
        html.window.console.info("Could not load rest/league/getUsers.json");
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
      })
      .catchError((e) {
        print(e);
        userMetaLoaded = false;
        html.window.console.info("Could not load rest/team/getUsersMeta.json");
      });
  } 
  
  void deleteUser(){
    html.window.console.info("In deleteUser() with selected user: " + selectedUser.toString());
    int index = userList.indexOf(userList.where((User) => User.id == selectedUser).first);
    html.window.console.info("In deleteUser() with index: " + index.toString());
    html.window.console.info(userList.where((User) => User.id == selectedUser).first);
    html.window.console.info(userList.elementAt(index));
    
    _http.post('rest/user/deleteUser.json', JSON.encode(userList.elementAt(index)))
    .then((HttpResponse response) {
      User userResponse = new User.fromJson(response.data);
      assert(userResponse.id == userList.where((User) => User.id == selectedUser).first.id);
      assert(userResponse.userName == userList.where((User) => User.id == selectedUser).first.userName);
      assert(userResponse.created == userList.where((User) => User.id == selectedUser).first.created);
      userList.removeAt(index);
      confirmDelete = false;
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/user/deleteUser.json");
    });
    selectedUser = -1;
    html.window.console.info("Deleting user by id: " + selectedUser.toString() + " succeded!");
  }
    
  void selectUser(int id){
    html.window.console.info("Selected user: " + id.toString());
    if(selectedUser == id){
      selectedUser = -1;
    } else {
      selectedUser = id;
    }
  }
  
  
  bool isActive(int id){
   return selectedUser == id;
  }
  
  String getSelectedUser(){
    return "Duuuud";
  }
  
  void cancel(){
    confirmDelete = false;
  }
  
  void setConfirmDelete(){
    confirmDelete = true;
  }
  
  dynamic myEncode(dynamic item) {
    if(item is DateTime) {
      return item.toIso8601String();
    }
    return item;
  }
 
}