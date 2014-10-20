part of fremad;

@Component(
    selector: 'admin-teams-view',
    templateUrl: 'packages/fremad/components/admin/teams_view.html',
    useShadowDom: false
)
class ShowAdminTeamsComponent {
  final Http _http;
  
  String name;
  int onlineId;
  
  ShowAdminTeamsComponent(this._http);
  
  void create() {
    html.window.console.info("Is in ShowAdminTeamsComponent");
  }
  
  void add(){
    Team team = new Team(-1, name, onlineId);
    _http(method: 'POST', url: 'rest/team/add', data: JSON.encode(team));
    html.window.console.info("Added team: " + name + " succeded!");
  }
}