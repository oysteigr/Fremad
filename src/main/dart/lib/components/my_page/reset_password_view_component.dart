part of fremad;

@Component(
    selector: 'my-page-reset-password-view',
    templateUrl: 'packages/fremad/components/my_page/reset_password_view.html',
    cssUrl: 'packages/fremad/components/my_page/reset_password_view.css',
    useShadowDom: false
)
class ShowMyPageResetPasswordComponent {
  final Http _http;
  String querystring = "";
  String message = "";
  
  
  ShowMyPageResetPasswordComponent(this._http){
    querystring = html.window.location.href;
    if(querystring.split("?").length == 1){
      message = "There is no point for you to be here...";
    }else{
      querystring = querystring.split("?")[1];
      html.window.console.info("querystring: " + querystring);
      resetPasswordUser(querystring);
    }
    
  }
  
  void resetPasswordUser(String querystring){
    html.window.console.info("In resetUserPassword() with string: " + querystring);
    bool success;
    _http.post('rest/user/resetUserPassword.json', querystring)
    .then((HttpResponse response) {
      String stringResponse = response.data.toString;
      success = response.data.toString() == 'true';
      message = "Password for user account has been reset!";
    })
    .catchError((HttpResponse response) {
      if(response.status == 400){
        message = response.data.toString();
      }else{
        message= "This account does not exist or something else went wrong. Try register again";
      }
      html.window.console.info("Could not load rest/user/resetUserPassword.json");
    });
  }
}