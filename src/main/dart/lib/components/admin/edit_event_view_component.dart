part of fremad;

@Component(
    selector: 'admin-edit-event-view',
    templateUrl: 'packages/fremad/components/admin/edit_event_view.html',
    cssUrl: 'packages/fremad/components/admin/edit_event_view.css',
    useShadowDom: false
)
class ShowAdminEditEventComponent {

  final Http _http;
  
  String _matchId(routeProvider) => routeProvider.parameters["matchId"];
  
  MatchEntry match;
  List<Event> events;
  
  ShowAdminEditEventComponent(this._http, RouteProvider routeProvider){
    html.window.console.info("ShowAdminEditEventComponent()");
    int matchId = int.parse(_matchId(routeProvider));
    loadMatchEvents(matchId);
    loadMatch(matchId);
  }
  
  void loadMatchEvents(int matchId) {
      html.window.console.info("Is in loadMatchEvents");
      _http.post('rest/event/getEvents.json', JSON.encode(matchId))
        .then((HttpResponse response) {
          print(response);
          EventList eventListObject = new EventList.fromJson(response.data);
          events = eventListObject.eventList;
          html.window.console.info("Success on loading events");
        })
        .catchError((e) {
          print(e);
          html.window.console.info("Could not load rest/team/getTeams.json");
        });
  } 
  
  void loadMatch(int matchId) {
    html.window.console.info("locaMatch() called");
    _http.post('rest/match/getMatch.json', JSON.encode(matchId))
      .then((HttpResponse response) {
        print(response);
        match = new MatchEntry.fromJson(response.data);
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/match/getMatch.json");
      });
  }
}