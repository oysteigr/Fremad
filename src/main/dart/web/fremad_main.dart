library fremad_main;

import 'package:angular/application_factory.dart';
import 'package:di/di.dart';
import 'package:logging/logging.dart';
import 'package:fremad/fremad.dart';

main(){
  Logger.root.level = Level.FINEST;
  Logger.root.onRecord.listen((LogRecord r) { print(r.message); });

  final inj = applicationFactory().addModule(new FremadApp()).run();
  GlobalHttpInterceptors.setUp(inj);
}
/*
class MyAppModule extends Module {
  MyAppModule() {
    bind(NgRoutingUsePushState, toValue: new NgRoutingUsePushState.value(false));
    bind(RouteInitializerFn, toImplementation: Routes);
    
  }
}

void main() {
  applicationFactory()
    .addModule(new MyAppModule())
    .run();
}

@Injectable()
class Routes 
{
  void call(Router router, RouteViewFactory views) {
    views.configure({
      'home': ngRoute(
          path: '/home',
          defaultRoute: true,
          view: 'views/home.html'),
      'theClub': ngRoute(
          path: '/theClub',
          view: 'views/theClub.html'),
      'team': ngRoute(
          path: '/team', 
          view: 'views/team.html'),
      'sponsors': ngRoute(
          path: '/sponsors', 
          view: 'views/sponsors.html'),
      'loggIn': ngRoute(
          path: '/loggIn', 
          view: 'views/loggIn.html')
    });
  }
}*/