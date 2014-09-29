library dart_route_checker;

import 'package:angular/angular.dart';
import 'package:angular/application_factory_static.dart';
import 'fremad_generated_type_factory_maps.dart' show setStaticReflectorAsDefault;
import 'fremad_static_expressions.dart' as generated_static_expressions;
import 'fremad_static_metadata.dart' as generated_static_metadata;

class MyAppModule extends Module {
  MyAppModule() {
    bind(NgRoutingUsePushState, toValue: new NgRoutingUsePushState.value(false));
    bind(RouteInitializerFn, toImplementation: Routes);
  }
}

void main() {
  setStaticReflectorAsDefault();
  staticApplicationFactory(generated_static_metadata.typeAnnotations, generated_static_expressions.getters, generated_static_expressions.setters, generated_static_expressions.symbols)
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