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
  Router router;
  List<TableEntry> tableEntryListe;
  
  ShowTeamTableComponent(this._http, this.router){
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
  String get dud => "ei";
  List<String> get _app{
    return appointments;
  }
  
  var thing = 0;
  final items = [];

  void addItem() {
    items.add("Thing ${thing++}");
  }

  void removeItem() {
    if (items.isNotEmpty) items.removeLast();
  }
}