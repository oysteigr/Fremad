part of fremad;

@Component(
    selector: 'article-match-view',
    templateUrl: 'packages/fremad/components/article/article_match.html',
    cssUrl: 'packages/fremad/components/article/article_match.css',
    useShadowDom: false
)


class ShowArticleMatchComponent {
  final Http _http;
  
  int articleId;
  
  bool teamLoaded = false;
  bool articleLoaded = false;
  bool reportLoaded = false;
  bool matchLoaded = false;
  
  Team team;
  Article article;
  MatchReport report;
  MatchEntry match;
  
  ShowArticleMatchComponent(this._http, RouteProvider routeProvider){
    articleId = int.parse(routeProvider.parameters["articleId"]);
    
    team = new Team(-1, "", -1);
    article = new Article(-1, -1, new DateTime.now(), "MATCH", "", "", "", "",false);
    report = new MatchReport(-1, -1, 0, 0, 0, 0, 0, false);
    match = new MatchEntry(-1, -1, -1, false, -1, "", -1, -1, new DateTime.now(), "");
   
    loadArticle();
    loadReport();
  }
  
  void loadArticle() {
    html.window.console.info("Is in loadArticles");
    articleLoaded = false;
    _http.post('rest/article/getArticle.json', articleId)
      .then((HttpResponse response) {
        print(response);
        article = new Article.fromJson(response.data);
        articleLoaded = true;
        html.window.console.info("Success on loading article");
      })
      .catchError((e) {
        print(e);
        articleLoaded = false;
        html.window.console.info("Could not load rest/article/getArticles.json");
      });
  } 
  
  void loadReport() {
    html.window.console.info("Is in loadReport");
    reportLoaded = false;
    _http.post('rest/article/getMatchReport.json', articleId)
      .then((HttpResponse response) {
        print(response);
        report = new MatchReport.fromJson(response.data);
        reportLoaded = true;
        loadMatch(report.matchId);
        html.window.console.info("Success on loading report");
      })
      .catchError((e) {
        print(e);
        reportLoaded = false;
        html.window.console.info("Could not load rest/article/getMatchReport.json");
      });
  } 
  
  void loadMatch(int matchId) {
    html.window.console.info("Is in loadMatch");
    matchLoaded = false;
    _http.post('rest/match/getMatch.json', matchId)
      .then((HttpResponse response) {
        print(response);
        match = new MatchEntry.fromJson(response.data);
        matchLoaded = true;
        loadTeam(match.fremadTeam);
        html.window.console.info("Success on loading match");
      })
      .catchError((e) {
        print(e);
        matchLoaded = false;
        html.window.console.info("Could not load rest/match/getMatch.json");
      });
  } 
  
  void loadTeam(int teamId) {
    html.window.console.info("Is in loadTeam");
    teamLoaded = false;
    _http.post('rest/team/getTeam.json', teamId)
      .then((HttpResponse response) {
        print(response);
        team = new Team.fromJson(response.data);
        teamLoaded = true;
        html.window.console.info("Success on loading team");
      })
      .catchError((e) {
        print(e);
        teamLoaded = false;
        html.window.console.info("Could not load rest/team/getTeam.json");
      });
  } 
  
  String getHomeTeamName(MatchEntry match) {
    if (match.homeMatch) {
      return team.name;
    } else {
      return match.opposingTeamName;
    }
  }
  
  String getAwayTeamName(MatchEntry match) {
    if (! match.homeMatch) {
      return team.name;
    } else {
      return match.opposingTeamName;
    }
  }
}