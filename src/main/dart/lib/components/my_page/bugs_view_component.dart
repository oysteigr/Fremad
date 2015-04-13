part of fremad;

@Component(
    selector: 'my-page-bugs-view',
    templateUrl: 'packages/fremad/components/my_page/bugs_view.html',
    cssUrl: 'packages/fremad/components/my_page/bugs_view.css',
    useShadowDom: false
)
class ShowMyPageBugsComponent {
  final Http _http;
  
  Bug currentBug;
  
  ShowMyPageBugsComponent(this._http){
    resetForm();
  }
  
  void reportBug(){
    html.window.console.debug(currentBug);
    currentBug.date = new DateTime.now();
    _http.post('rest/technical/addBug.json', JSON.encode(currentBug))
      .then((HttpResponse response) {
        print(response);
        MESSAGE.addSuccessMessage("Thank you for reporting this bug");
        html.window.console.info("Success on adding bug");
        resetForm();
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/technical/addBug.json");
      });
    
  }
  
  void resetForm(){
    currentBug = new Bug(-1, -1, 4, "", html.window.navigator.platform, browser.toString(), "", null, false);
  }
}