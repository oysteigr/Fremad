part of fremad;

@Component(
    selector: 'my-page-validate-view',
    templateUrl: 'packages/fremad/components/my_page/validate_view.html',
    cssUrl: 'packages/fremad/components/my_page/validate_view.css',
    useShadowDom: false
)
class ShowMyPageValidateComponent {
  final Http _http;
  String querystring = "";
  String message = "";
  
  
  ShowMyPageValidateComponent(this._http){
    querystring = html.window.location.href;
    if(querystring.split("?").length == 1){
      message = "There is no point for you to be here...";
    }else{
      querystring = querystring.split("?")[1];
      html.window.console.info("querystring: " + querystring);
      validateUser(querystring);
    }
    
  }
  
  void validateUser(String querystring){
    html.window.console.info("In validateUser() with string: " + querystring);
    bool success;
    _http.post('rest/user/validateUser.json', querystring)
    .then((HttpResponse response) {
      String stringResponse = response.data.toString;
      success = response.data.toString() == 'true';
      message = "User account has been activated!";
    })
    .catchError((HttpResponse response) {
      if(response.status == 400){
        message = response.data.toString();
      }else{
        message= "This account does not exist or something else went wrong. Try register again";
      }
      html.window.console.info("Could not load rest/user/validateUser.json");
    });
  }
}