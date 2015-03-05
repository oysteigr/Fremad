part of fremad;

@Component(
    selector: 'admin-view',
    templateUrl: 'packages/fremad/components/admin_view.html',
    cssUrl: 'packages/fremad/components/admin_view.css',
    useShadowDom: false
)
class ShowAdminComponent {
  
  bool hideMenuMobile = true;
  
  
  bool showBasedOnRole(int role){
    return USER.checkUserRole(role);
  }
  
  String getMobileClass(){
    return hideMenuMobile ? "hiding" : "showing";
  }
}