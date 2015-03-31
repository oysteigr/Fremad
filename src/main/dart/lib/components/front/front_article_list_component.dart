part of fremad;

@Component(
    selector: 'front-article-list',
    templateUrl: 'packages/fremad/components/front/front_article_list.html',
    cssUrl: 'packages/fremad/components/front/front_article_list.css',
    useShadowDom: false
)
class FrontArticleListComponent {
  
  List<Article> articleList = new List<Article>();
  Article selectedArticle = null;
  
  FrontArticleListComponent(){
    createDummyArticles();
    if(articleList.length > 0){
      selectedArticle = articleList[0];
    }
  }
  
  void selectArticle(int id){
    int index = articleList.indexOf(articleList.where((Article) => Article.id == id).first);
    selectedArticle = articleList[index];
  }
  
  void create() {
    print("In this func");
  }
  
  bool checkWidth(int width){
    return width < html.window.screen.width;
  }
  
  void createDummyArticles(){
    Article article1 = new Article(1,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Fremad klare for femtedivisjon!", 
        "Opprykket ble sikret allerede før kampstart i vår kamp, og kampen mot BI ble derfor en ren formalitet - som vi gjennomførte i godkjent stil. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "images/article-images/opprykk.jpg",true
        );
    Article article2 = new Article(2,1,DateTime.parse("2014-10-12 18:00:00"),"NEWS", 
        "Murphys lov på Voksen kunstgress",
        "READY 2 - FREMAD FAMAGUSTA 3-2 (0-2) Det meste gikk galt da Fremad skulle sikre opprykket onsdag kveld.",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "images/article-images/murphys.jpg",true
        );
    Article article3 = new Article(3,1,DateTime.parse("2014-09-21 17:12:00"),"NEWS", 
        "4 kamper, 1 poeng",
        "Ringen FK - Fremad Famagusta 2 2-2 (1-0), Fremad Famagusta 2 - Oppsal 3 0-6 (0-2), Nordstrand 4 - Fremad Famagusta 2 11-0 (5-0) og Fremad Famagusta 2 - Hauketo 2 0-4 (0-2). ",
        "Fremad Famagusta kunne sikre opprykket til 5. divisjon mot Ready 2 onsdag kveld. Med tre kamper igjen å spille og en ledelse på åtte poeng ned til tredjeplassen på tabellen - den øverste plassen som ikke gir opprykk, krevdes det imidlertid full pott på Voksen kunstgress for at opprykksdrømmen skulle bli virkelighet allerede denne onsdagen.",
        "images/article-images/4kamper.jpg",true
        );
    Article article4 = new Article(4,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Fremad-toget ruller videre!",
        "FREMAD FAMAGUSTA - LOKOMOTIV OSLO 2 4-2 (2-2) Fremad hadde marginene på sin side og kjempet seg til tre poeng i tøff kamp mot god motstand. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "images/article-images/fremadtoget.jpg",true
        );
    Article article5 = new Article(5,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Klubbrekord!",
        "FREMAD FAMAGUSTA - HASLEB 3-0 (2-0) Fremad Famagusta tok sin 10. strake seier og et langt steg mot opprykk da Hasleb ble slått på Ekebergsletta onsdag kveld. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "images/article-images/rekord.jpg",true
        );
    articleList.add(article1);
    articleList.add(article2);
    articleList.add(article3);
    articleList.add(article4);
    articleList.add(article5);
  }

}

/*int id;
int authorId;
DateTime date;
String articleType;
String header;
String context;
String content;
String imageURL;*/