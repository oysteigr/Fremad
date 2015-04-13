part of fremad;

@Component(
    selector: 'my-page-feature-request-view',
    templateUrl: 'packages/fremad/components/my_page/feature_request_view.html',
    cssUrl: 'packages/fremad/components/my_page/feature_request_view.css',
    useShadowDom: false
)
class ShowMyPageFeatureRequestComponent {
  final Http _http;
  
  ShowMyPageFeatureRequestComponent(this._http){
 
  }

}