part of fremad;

@Component(
    selector: 'admin-match-reports-view',
    templateUrl: 'packages/fremad/components/admin/match_reports_view.html',
    cssUrl: 'packages/fremad/components/admin/match_reports_view.css',
    useShadowDom: false
)
class ShowAdminMatchReportsComponent {
  final Http _http;
  bool articleLoaded = false;
  bool matcheReportsLoaded = false;
  bool matchesLoaded = false;
  bool teamsLoaded = false;
  
  bool showImageUpload = false;
  bool isEditing = false;

  int selectedTeam;

  List<Team> teamList;
  List<Article> articleList;
  List<MatchReport> reportList;
  List<MatchEntry> matchList;
  
  Article currentArticle = new Article(-1, -1, new DateTime.now(), "MATCH", "", "", "", "",false);
  MatchReport currentReport = new MatchReport(-1, -1, 0, 0, 0, 0, 0, false);
  MatchEntry currentMatch = new MatchEntry(-1, -1, -1, false, -1, "", -1, -1, new DateTime.now(), "");
 
  bool useRatio = true;
  int xRatio = 610;
  int yRatio = 350;
  int minWith = 1000;
  int minHeight = 400;
  
  
  ShowAdminMatchReportsComponent(this._http){
    selectedTeam = 9;
    loadMatches();
    loadTeams();
    loadMatchReports();
  }
  
  void loadArticle() {
    html.window.console.info("Is in loadArticles");
    articleLoaded = false;
    _http.post('rest/article/getArticle.json', currentReport.articleId)
      .then((HttpResponse response) {
        print(response);
        currentArticle = new Article.fromJson(response.data);
        articleLoaded = true;
        html.window.console.info("Success on loading articles");
      })
      .catchError((e) {
        print(e);
        articleLoaded = false;
        html.window.console.info("Could not load rest/article/getArticles.json");
      });
  } 
  
  void loadMatches() {
    html.window.console.info("Is in loadMatches");
    matchesLoaded = false;
    _http.get('rest/match/getThisYearsMatches.json')
      .then((HttpResponse response) {
        print(response);
        MatchList matchListObject = new MatchList.fromJson(response.data);
        matchList = matchListObject.matchEntryList;
        matchesLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        matchesLoaded = false;
        html.window.console.info("Could not load rest/match/getMathes.json");
      });
  }
  
  void loadTeams() {
    html.window.console.info("Is in loadTeams");
    teamsLoaded = false;
    _http.get('rest/team/getTeams.json')
      .then((HttpResponse response) {
        print(response);
        TeamList teamListObject = new TeamList.fromJson(response.data);
        teamList = teamListObject.teamList;
        teamsLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        teamsLoaded = false;
        html.window.console.info("Could not load rest/team/getTeams.json");
      });
  } 
  
  void loadMatchReports() {
    html.window.console.info("Is in loadMatcheReports");
    matcheReportsLoaded = false;
    _http.get('rest/article/getMatchReports.json')
      .then((HttpResponse response) {
        print(response);
        MatchReportList reportListObject = new MatchReportList.fromJson(response.data);
        reportList = reportListObject.matchReportList;
        matcheReportsLoaded = true;
        html.window.console.info("Success on loading table");
      })
      .catchError((e) {
        print(e);
        matcheReportsLoaded = false;
        html.window.console.info("Could not load rest/article/getMatchReports.json");
      });
  }
  
  void saveArticle() {
    html.window.console.info("Is in saveArticle");
    _http.post('rest/article/addArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        currentArticle = new Article.fromJson(response.data);
        html.window.console.info("Success on adding article");
        saveReport(currentArticle.id);
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/addArticle.json");
      });
  } 
  
  void saveReport(int articleId) {
    html.window.console.info("Is in saveReport");
    currentReport.articleId = articleId;
    _http.post('rest/article/addMatchReport.json', currentReport)
      .then((HttpResponse response) {
        print(response);
        currentReport = new MatchReport.fromJson(response.data);
        html.window.console.info("Success on adding article");
        MESSAGE.addSuccessMessage("Page added");
        reportList.add(currentReport);
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/addMatchReport.json");
      });
  } 
  
  void updateArticle() {
    html.window.console.info("Is in updateArticle");
    _http.post('rest/article/updateArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        updateReport();
        html.window.console.info("Success on updating article");
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/updateArticle.json");
      });
  } 
  
  void updateReport() {
    html.window.console.info("Is in updateReport");
    currentReport.published = currentArticle.published;
    _http.post('rest/article/updateMatchReport.json', currentReport)
      .then((HttpResponse response) {
        print(response);
        MESSAGE.addSuccessMessage("Report updated");
        html.window.console.info("Success on updating report");
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/updateMatchReport.json");
      });
  } 
  
  void deleteArticle() {
    html.window.console.info("Is in deleteArticle");
    _http.post('rest/article/deleteArticle.json', currentArticle)
      .then((HttpResponse response) {
        print(response);
        back();
        Article articleResponse = new Article.fromJson(response.data);
        articleList.removeWhere((Article) => Article.id == articleResponse.id);
        html.window.console.info("Success on deleting article");
        
      })
      .catchError((e) {
        print(e);
        html.window.console.info("Could not load rest/article/deleteArticle.json");
      });
  } 
  
  void selectMatch(MatchEntry match){
    currentMatch = match;
    if(reportList.where((Report) => Report.matchId == match.id).length != 0){
      currentReport = reportList.where((Report) => Report.matchId == match.id).first;
      loadArticle();
    }else{
      currentReport = new MatchReport(match.id, -1, 0, 0, 0, 0, 0, false);
      addNewArticle();
    }
    isEditing = true;
    html.window.console.info(match.opposingTeamName);
  }
  
  bool filter(MatchEntry match){
    if (selectedTeam == 9 || selectedTeam == match.fremadTeam) {
      return true;
    }
    return false;
  }
  
  void unPublish(){
    currentArticle.date = new DateTime.now();
    currentArticle.published = false;
    updateArticle();
  }
  
  void publish(){
    currentArticle.published = true;
    updateArticle();
  }
  
  String getPublishedState(MatchEntry match){
    if(!matcheReportsLoaded){
      return "";
    }
    if(reportList.where((Report) => Report.matchId == match.id).length != 0){
      return reportList.where((Report) => Report.matchId == match.id).first.published ? "published" : "unpublished"; 
    }
    return "no report"; 
  }
  
  void addNewArticle(){
    currentArticle = new Article(-1, -1, new DateTime.now(), "MATCH", "", "", "", "",false);
    isEditing = true;
  }
  void showImageEditor(){
    showImageUpload = true;
  }
  
  void cancelImage(){
    showImageUpload = false;
  }
  void back(){
    currentArticle = new Article(-1, -1, new DateTime.now(), "MATCH", "", "", "", "",false);
    currentReport = new MatchReport(-1, -1, 0, 0, 0, 0, 0, false);
    isEditing = false;
  }
  
  String getHomeTeamName(MatchEntry match) {
    if(match.id == -1){
      return "";
    }
    if (match.homeMatch) {
      return teamList.where((Team) => Team.id == match.fremadTeam).first.name;
    } else {
      return match.opposingTeamName;
    }
  }
  
  String getAwayTeamName(MatchEntry match) {
    if(match.id == -1){
      return "";
    }
    if (! match.homeMatch) {
      return teamList.where((Team) => Team.id == match.fremadTeam).first.name;
    } else {
      return match.opposingTeamName;
    }
  }
}