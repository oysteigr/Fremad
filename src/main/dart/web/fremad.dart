library dart_route_checker;

import 'package:angular/angular.dart';
import 'package:angular/application_factory.dart';

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
      'home': ngRoute(path: '/home', defaultRoute: true, view: 'views/home.html'),
      'theClub': ngRoute(path: '/theClub', view: 'views/theClub.html'),
      'team': ngRoute(path: '/team', view: 'views/team.html'),
      'sponsors': ngRoute(path: '/sponsors', view: 'views/sponsors.html'),
      'loggIn': ngRoute(path: '/loggIn', view: 'views/loggIn.html')
    });
  }
}