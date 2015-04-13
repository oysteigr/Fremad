part of fremad;

@Component(
    selector: 'my-page-view',
    templateUrl: 'packages/fremad/components/my_page_view.html',
    cssUrl: 'packages/fremad/components/my_page_view.css',
    useShadowDom: false
)
class ShowMyPageComponent {
  final Http _http;
  bool hideMenuMobile = true;
  
  ShowMyPageComponent(this._http){

 //   leagueList.add(new League(2166, 2013, 0));
  }

  
  bool showBasedOnRole(int role){
    return USER.checkUserRole(role);
  }
  
  String getMobileClass(){
    return hideMenuMobile ? "hiding" : "showing";
  }
 
}