part of fremad;

@Component(
    selector: 'admin-role-request-view',
    templateUrl: 'packages/fremad/components/admin/role_request_view.html',
    cssUrl: 'packages/fremad/components/admin/role_request_view.css',
    useShadowDom: false
)
class ShowAdminRoleRequestComponent {
  final Http _http;
  bool requestsLoaded;
  bool userMetaLoaded;
  bool showAccepted = false;
  String errorMessage = "";
  
  UserMetaList userMetaListObject;
  List<UserMeta> userMetaList;
  RoleRequestList roleRequestListObject;
  List<RoleRequest> roleRequestList;
  int selectedRequest = -1;
  
  
  
  ShowAdminRoleRequestComponent(this._http){
    loadRequests();
    loadUsersMeta();

 //   leagueList.add(new League(2166, 2013, 0));
  }
  
  void loadRequests() {
    html.window.console.info("Is in loadUsers");
    requestsLoaded = false;
    _http.get('rest/user/getUserRoleRequests.json')
      .then((HttpResponse response) {
        print(response);
        roleRequestListObject = new RoleRequestList.fromJson(response.data);
        roleRequestList = roleRequestListObject.roleRequestList;
        requestsLoaded = true;
        html.window.console.info("Success on loading requests");
      })
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        requestsLoaded = false;
        html.window.console.info("Could not load rest/user/getUserRoleRequests.json");
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
      .catchError((HttpResponse response) {
        if(response.status == 400){
          errorMessage = response.data.toString();
        }
        userMetaLoaded = false;
        html.window.console.info("Could not load rest/team/getUsersMeta.json");
      });
  } 
  
  void deleteRequest(){
    int index = roleRequestList.indexOf(roleRequestList.where((RoleRequest) => RoleRequest.id == selectedRequest).first);
    
    _http.post('rest/user/deleteUserRoleRequest.json', JSON.encode(roleRequestList.elementAt(index)))
    .then((HttpResponse response) {
      RoleRequest requestResponse = new RoleRequest.fromJson(response.data);
      assert(requestResponse.id == roleRequestList.where((RoleRequest) => RoleRequest.id == roleRequestList).first.id);
      assert(requestResponse.userId == roleRequestList.where((RoleRequest) => RoleRequest.id == roleRequestList).first.userId);
      assert(requestResponse.date == roleRequestList.where((RoleRequest) => RoleRequest.id == roleRequestList).first.date);
      roleRequestList.removeAt(index);
    })
    .catchError((e) {
      print(e);
      html.window.console.info("Could not load rest/user/deleteUserRoleRequest.json");
    });
    html.window.console.info("Deleting request by id: " + selectedRequest.toString() + " succeded!");
    selectedRequest = -1;
  }
  
  void acceptRequest(){
    int index = roleRequestList.indexOf(roleRequestList.where((RoleRequest) => RoleRequest.id == selectedRequest).first);
    
    html.window.console.info("Is in acceptRequest");
    _http.post('rest/user/grantUserRoleRequest.json', JSON.encode(roleRequestList.elementAt(index)))
      .then((HttpResponse response) {
        if(response.status == 204){
          html.window.console.info("Could not accept");
          return;
        }
        print(response);
        html.window.console.info("Success on accepting user meta");
        roleRequestList.where((RoleRequest) => RoleRequest.id == selectedRequest).first.accepted = true;
        selectedRequest = -1;
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/user/grantUserRoleRequest.json");
      });

  }
  
  bool filter(RoleRequest role){
    return role.accepted == showAccepted;
  }
  
  void selectRequest(int id){
    html.window.console.info("Selected request: " + id.toString());
    if(selectedRequest == id){
      selectedRequest = -1;
    } else {
      selectedRequest = id;
    }
  }
  
  
  bool isActive(int id){
   return selectedRequest == id;
  }
  
  String getRoleFromRequest(RoleRequest role){
    return RoleRequest.parseRole(role.requestedRole);
  }
  
  String getFullNameFromUser(int userId){
    UserMeta returnedUserMeta = getMetaFromUser(userId);
    if(returnedUserMeta == null){
      return "UserByNoName";
    }
    return getMetaFromUser(userId).getFullName();
  }
  
  UserMeta getMetaFromUser(int userId){
    if(!userMetaLoaded){
      return null;
    }
    List<UserMeta> list = userMetaList.where((UserMeta) => UserMeta.userId == userId);
    if(list.length == 0){
      return null;
    }
    return list.first;
  }
  
  void setShowAccepted(bool show){
    showAccepted = show;
  }
  
}