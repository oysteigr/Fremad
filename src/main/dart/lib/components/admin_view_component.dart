part of fremad;

@Component(
    selector: 'admin-view',
    templateUrl: 'packages/fremad/components/admin_view.html',
    cssUrl: 'packages/fremad/components/admin_view.css',
    useShadowDom: false
)
class ShowAdminComponent {
  void create() {
    print("In this func");
  }
}