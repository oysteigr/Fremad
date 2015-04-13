part of fremad;

@Component(
    selector: 'my-page-updates-view',
    templateUrl: 'packages/fremad/components/my_page/updates_view.html',
    cssUrl: 'packages/fremad/components/my_page/updates_view.css',
    useShadowDom: false
)
class ShowMyPageUpdatesComponent {
  final Http _http;

  ShowMyPageUpdatesComponent(this._http){

  }
 
}