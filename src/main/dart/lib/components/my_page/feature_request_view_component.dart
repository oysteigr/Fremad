part of fremad;

@Component(
    selector: 'my-page-feature-request-view',
    templateUrl: 'packages/fremad/components/my_page/feature_request_view.html',
    cssUrl: 'packages/fremad/components/my_page/feature_request_view.css',
    useShadowDom: false
)
class ShowMyPageFeatureRequestComponent {
  final Http _http;
  
  FeatureReqeust currentRequest;
  
  ShowMyPageFeatureRequestComponent(this._http){
    resetForm();
  }
  
  void requestFeature(){
    html.window.console.debug(currentRequest);
    currentRequest.date = new DateTime.now();
    _http.post('rest/technical/addFeatureRequest.json', JSON.encode(currentRequest))
      .then((HttpResponse response) {
        print(response);
        MESSAGE.addSuccessMessage("Thank you for requesting a feature");
        html.window.console.info("Success on adding request");
        resetForm();
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/technical/addFeatureRequest.json");
      });
    
  }
  
  void resetForm(){
    currentRequest = new FeatureReqeust(-1, -1, "", "", null, false);
  }
}