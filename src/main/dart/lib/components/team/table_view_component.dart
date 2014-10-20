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
  List<TableEntry> tableEntryListe;
  
  ShowTeamTableComponent(this._http){
    _loadData();
  }
  
  void create() {
    print("In this func");
  }
  void _loadData() {
    html.window.console.info("Is in _loadData");
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