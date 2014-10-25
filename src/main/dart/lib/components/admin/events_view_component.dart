part of fremad;

@Component(
    selector: 'admin-events-view',
    templateUrl: 'packages/fremad/components/admin/events_view.html',
    useShadowDom: false
)
class ShowAdminEventsComponent {
  void create() {
    html.window.console.info("Is in ShowAdminEventsComponent");
  }

  
}