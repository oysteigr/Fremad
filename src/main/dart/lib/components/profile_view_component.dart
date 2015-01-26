part of fremad;

@Component(
    selector: 'profile-view',
    templateUrl: 'packages/fremad/components/profile_view.html',
    cssUrl: 'packages/fremad/components/profile_view.css',
    useShadowDom: false
)
class ShowProfileComponent {
  final Http _http;
  bool userMetaLoaded = false;
  bool showErrors = false;
  bool success = false;
  UserMeta userMeta = null;
  
  
  ShowProfileComponent(this._http){
    loadUserMeta();

 //   leagueList.add(new League(2166, 2013, 0));
  }
  
  
  void loadUserMeta() {
    html.window.console.info("Is in loadUserMeta with user: " + USER.getUserId().toString());
    userMetaLoaded = false;
    _http.post('rest/user/getUserMeta.json', JSON.encode(USER.getUserId()))
      .then((HttpResponse response) {
        print(response);
        userMeta = new UserMeta.fromJson(response.data);
        userMetaLoaded = true;
        html.window.console.info("Success on loading user meta");
      })
      .catchError((e) {
        print(e);
        userMetaLoaded = false;
        html.window.console.info("Could not load rest/user/getUserMeta.json");
      });
  } 
  
  void updateMeta(){
    html.window.console.info("Is in updateMeta");
    userMetaLoaded = false;
    _http.post('rest/user/updateUserMeta.json', JSON.encode(userMeta))
      .then((HttpResponse response) {
        if(response.status == 204){
          html.window.console.info("Could not update");
          return;
        }
        print(response);
        userMeta = new UserMeta.fromJson(response.data);
        success = true;
        html.window.console.info("Success on updating user meta");
      })
      .catchError((e) {
        print(e);
        userMetaLoaded = false;
        html.window.console.info("Could not load rest/user/updateUserMeta.json");
      });
  }
  
  bool verifyPhoneNumber(){
    return true;
  }
  
  bool verifyHomeTown(){
    return true;
  }
  
  bool verifyProfession(){
    return true;
  }
    
 
}