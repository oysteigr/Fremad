part of fremad;

@Component(
    selector: 'team-table-view',
    templateUrl: 'packages/fremad/components/team/table_view.html',
    useShadowDom: false
)
class ShowTeamTableComponent {
  final Http _http;
  Table table;
  bool tableLoaded;
  String message;
  int teamID;
  List<TableEntry> tableEntryListe;
  
  ShowTeamTableComponent(this._http, RouteProvider routeProvider){
    teamID = int.parse(routeProvider.parameters["teamId"]);
    html.window.console.info("RouteProvider in table found id: " + teamID.toString());
    loadTable();
  }
  
  void loadTable() {
    html.window.console.info("Is in loadTable");
    tableLoaded = false;
    _http.get('table.json')
      .then((HttpResponse response) {
        print(response);
        table = new Table.fromJson(response.data);
        tableEntryListe = table.tableEntryList;
        tableLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        tableLoaded = false;
        message = "Could not load table.json";
      });
  }
}