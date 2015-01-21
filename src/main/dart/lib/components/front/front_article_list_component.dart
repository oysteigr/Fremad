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
    Article article1 = new Article(1,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", "Fremad klare for femtedivisjon!", "Opprykket ble sikret allerede før kampstart i vår kamp, og kampen mot BI ble derfor en ren formalitet - som vi gjennomførte i godkjent stil. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "http://idrett.speaker.no/Downloads/606242/pics/Opprykk.jpg",true
        );
    Article article2 = new Article(2,1,DateTime.parse("2014-10-12 18:00:00"),"NEWS", 
        "Duket for thrilleravslutning etter nok et tap",
        "Det var duket for toppkamp på Ekebergsletta onsdag kveld da førsteplasserte Fremad Famagusta tok imot andreplasserte Majorstuen. Med to poengs luke mellom lagene før avspark, og med kun to kamper igjen av sesongen var forutsetningene for Fremad Famagustas del klare. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "http://idrett.speaker.no/Downloads/606242/pics/tabell_20140918.png",true
        );
    Article article3 = new Article(3,1,DateTime.parse("2014-09-21 17:12:00"),"NEWS", 
        "Murphys lov på Voksen kunstgress",
        "READY 2 - FREMAD FAMAGUSTA 3-2 (0-2) Det meste gikk galt da Fremad skulle sikre opprykket onsdag kveld. ",
        "Fremad Famagusta kunne sikre opprykket til 5. divisjon mot Ready 2 onsdag kveld. Med tre kamper igjen å spille og en ledelse på åtte poeng ned til tredjeplassen på tabellen - den øverste plassen som ikke gir opprykk, krevdes det imidlertid full pott på Voksen kunstgress for at opprykksdrømmen skulle bli virkelighet allerede denne onsdagen.",
        "http://toolbox.n3sport.no/Downloads/606242/pics/20140910_-_Ready_2.jpg",true
        );
    Article article4 = new Article(4,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Fremad klare for femtedivisjon!",
        "Opprykket ble sikret allerede før kampstart i vår kamp, og kampen mot BI ble derfor en ren formalitet - som vi gjennomførte i godkjent stil. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "http://idrett.speaker.no/Downloads/606242/pics/Opprykk.jpg",true
        );
    Article article5 = new Article(5,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Fremad klare for femtedivisjon!",
        "Opprykket ble sikret allerede før kampstart i vår kamp, og kampen mot BI ble derfor en ren formalitet - som vi gjennomførte i godkjent stil. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "http://idrett.speaker.no/Downloads/606242/pics/Opprykk.jpg",true
        );
    Article article6 = new Article(6,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Fremad klare for femtedivisjon!",
        "Opprykket ble sikret allerede før kampstart i vår kamp, og kampen mot BI ble derfor en ren formalitet - som vi gjennomførte i godkjent stil. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "http://idrett.speaker.no/Downloads/606242/pics/Opprykk.jpg",true
        );
    Article article7 = new Article(7,1,DateTime.parse("2014-10-20 20:18:00"),"NEWS", 
        "Fremad klare for femtedivisjon!",
        "Opprykket ble sikret allerede før kampstart i vår kamp, og kampen mot BI ble derfor en ren formalitet - som vi gjennomførte i godkjent stil. ",
        "Det var duket for opprykksthriller i 6. divisjon avdeling 2 onsdag 24. september. Først møttes Indre Østham og Majorstuen klokken 17.30, før det var duket for BI Athletics mot Fremad Famagusta klokken 20.15. Dersom Majorstuen tok poeng mot Indre Østham ville Fremads opprykk være sikret allerede før vår egen kamp skulle sparkes i gang. Dersom Indre Østham derimot skulle slå Majorstuen ville Fremad trenge tre poeng mot BI Athletics.\n Kampen mellom Majorstuen og Indre Østham ble en jevnspilt og dramatisk affære, hvor Indre Østham - etter å ha ligget under med 2-0 til pause - kjempet seg inn i kampen igjen etter hvilen. De hvite og grønne fra Indre Østfold hadde flere gode sjanser til å snu kampen, men noe uflaks og udyktighet i de avgjørende øyeblikkene gjorde at det kun ble med en redusering, og kampen endte 2-1 til Majorstuen. Dette resultatet betød at Majorstuen vant avdelingen, at Indre Østham ikke greide å avansere fra sin tredjeplass, og ikke minst at",
        "http://idrett.speaker.no/Downloads/606242/pics/Opprykk.jpg",true
        );
    articleList.add(article1);
    articleList.add(article2);
    articleList.add(article3);
    articleList.add(article4);
    articleList.add(article5);
    articleList.add(article6);
    articleList.add(article7);
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