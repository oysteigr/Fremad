part of fremad;

@Component(
    selector: 'admin-users-view',
    templateUrl: 'packages/fremad/components/admin/users_view.html',
    cssUrl: 'packages/fremad/components/admin/users_view.css',
    useShadowDom: false
)
class ShowAdminUsersComponent {
  final Http _http;
  bool usersLoaded = false;
  bool userMetaLoaded = false;
  bool userAndMetaMerged = false;
  bool isEditing = false;
  bool confirmDelete = false;
  
  String errorMessage = "";
  
  UserList userListObject;
  UserMetaList userMetaListObject;
  
  List<User> userList;
  List<UserMeta> userMetaList;
  List<UserAndMeta> userAndMetaList;
  
  int selectedUser = -1;
  UserMeta currentMeta;
  int roleFilter = 9;
  
  
  ShowAdminUsersComponent(this._http){
    initMeta();
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
    html.window.console.info("In mergeUserAndMeta");
    for(int i = 0 ; i < userList.length ; i++){
      if(userMetaList.where((UserMeta) => UserMeta.userId == userList.elementAt(i).id).length == 1){
        userAndMetaList.add(new UserAndMeta(userList.elementAt(i), userMetaList.where((UserMeta) => UserMeta.userId == userList.elementAt(i).id).first));
      }else{
        userAndMetaList.add(new UserAndMeta(userList.elementAt(i), new UserMeta(userList.elementAt(i).id, "John", "Doe", "", new DateTime(1), "", "")));
      }
      userAndMetaList.sort((x, y) => x.userMeta.getFullName() .compareTo(y.userMeta.getFullName()));
      userAndMetaMerged = true;
      html.window.console.info("Done in mergeUserAndMeta");
    }
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
  
  void setRoleFilter(int role){ 
    roleFilter = role;
  }
  
  bool filter(User user){
    if(roleFilter == 9){
      return true;
    }else if(roleFilter == 8 && !user.validated){
      return true;
    }else if(user.role == roleFilter && user.validated){
      return true;
    }else if(user.role == 6 && roleFilter == 5 && user.validated){
      return true;
    }
    return false;
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
  
  String getRoleFromUser(User user){
    if(!user.validated){
      return "NOT VALIDATED";
    }
    return User.parseRole(user.role);
  }
  
  UserMeta getMetaFromUser(int userId){
    if(!userMetaLoaded){
      return null;
    }
    List<UserAndMeta> list = userAndMetaList.where((UserAndMeta) => UserAndMeta.user.id == userId);
    if(list.length == 0){
      return null;
    }
    return list.first.userMeta;
  }
  
  void setCurrentMeta(){
    UserMeta returnedUserMeta = getMetaFromUser(selectedUser);
    if(returnedUserMeta == null){
      initMeta();
    }else{
      currentMeta = returnedUserMeta;
    }
    
  }
  
  void initMeta(){
    currentMeta = new UserMeta(-1, "", "", "", new DateTime(1), "", "");
  }
  
  void setEditMode(){
    setCurrentMeta();
    isEditing = true;
  }
  
  void cancelMeta(){
    isEditing = false;
  }
  
  void updateUserMeta(){

    html.window.console.info("Is in updateUserMeta");
    _http.post('rest/user/updateUserMeta.json', JSON.encode(currentMeta))
      .then((HttpResponse response) {
        if(response.status == 204){
          html.window.console.info("Could not update");
          return;
        }
        print(response);
        currentMeta = new UserMeta.fromJson(response.data);
        html.window.console.info("Success on updating user meta");
        html.window.console.info(currentMeta);
        if(getMetaFromUser(currentMeta.userId) == null){
          userMetaList.add(currentMeta);
        }
        cancelMeta();
        selectedUser = -1;
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/user/updateUserMeta.json");
      });

  }
}