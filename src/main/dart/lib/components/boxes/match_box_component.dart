part of fremad;

@Component(
    selector: 'box-match-view',
    templateUrl: 'packages/fremad/components/boxes/match_box.html',
    cssUrl: 'packages/fremad/components/boxes/match_box.css',
    useShadowDom: false
)
class ShowBoxMatchComponent implements AttachAware{
  
  @NgOneWayOneTime("teamObject")
  Team team;
  
  final Http _http;
  bool nextMatchLoaded = false;
  bool prevMatchLoaded = false;
  bool teamLoaded = false;
  String message;
  MatchEntry nextMatch;
  MatchEntry prevMatch;
  
  ShowBoxMatchComponent(this._http, RouteProvider routeProvider){
    
  }
  
  void loadNextMatch() {
    html.window.console.info("Is in loadNextMatch");
    nextMatchLoaded = false;
    _http.post('rest/match/getNextMatch.json', JSON.encode(team.id))
      .then((HttpResponse response) {
        print(response);
        if(response.status == 204){
          html.window.console.info("Did not find nextMatch");
          nextMatch = null;
        }else{
          html.window.console.info("Found nextMatch");
          nextMatch = new MatchEntry.fromJson(response.data);
        }
        nextMatchLoaded = true;
        html.window.console.info("Success on loading nextMatch");
        loadPrevMatch();
      })
      .catchError((e) {
        print(e);
        nextMatchLoaded = false;
        html.window.console.info("Could not load rest/match/getNextMatch.json");
      });
  } 
  
  void loadPrevMatch() {
    html.window.console.info("Is in loadPrevMatch");
    prevMatchLoaded = false;
    _http.post('rest/match/getPrevMatch.json', JSON.encode(team.id))
      .then((HttpResponse response) {
        print(response);
        if(response.status == 204){
          html.window.console.info("Did not find prevMatch");
          prevMatch = null;
        }else{
          html.window.console.info("Found prevMatch");
          prevMatch = new MatchEntry.fromJson(response.data);
        }
        prevMatchLoaded = true;
        html.window.console.info("Success on loading prevMatch");
      })
      .catchError((e) {
        print(e);
        prevMatchLoaded = false;
        html.window.console.info("Could not load rest/match/getPrevMatch.json");
      });
  } 
  
  @override
  void attach() {
    html.window.console.info("Constructor in ShowBoxLastNextMatchComponent : teamId=" + team.id.toString());
     loadNextMatch();

  }
}