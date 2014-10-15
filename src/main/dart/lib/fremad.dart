library fremad;

import 'dart:html' as html;
import 'dart:async';
import 'dart:convert';

import 'package:angular/angular.dart';
//import 'package:angular/routing/module.dart';
import 'package:angular/animate/module.dart';

part 'components/header_component.dart';
part 'components/main_menu_component.dart';
part 'components/footer_component.dart';

part 'components/club_view_component.dart';
part 'components/home_view_component.dart';
part 'components/loggin_view_component.dart';
part 'components/sponsors_view_component.dart';
part 'components/team_view_component.dart';
part 'components/team_fremad_view_component.dart';

part 'services/messages.dart';

part 'fremad_route_initializer.dart';
part 'global_http_interceptors.dart';

class FremadApp extends Module {
  FremadApp(){
    
    
    bind(HeaderComponent);
    bind(MainMenuComponent);
    bind(FooterComponent);
    
    bind(ShowTheClubComponent);
    bind(ShowHomeComponent);
    bind(ShowLogginComponent);
    bind(ShowSponsorsComponent);
    bind(ShowTeamComponent);
    bind(ShowTeamFremadComponent);
    
    bind(Messages);
    
    bind(RouteInitializerFn, toValue: fremadRouteInitializer);
    bind(NgRoutingUsePushState, toValue: new NgRoutingUsePushState.value(false));

    bind(UrlRewriter, toImplementation: FremadUrlRewriter);
  }
}


@Injectable()
class FremadUrlRewriter implements UrlRewriter {
  String call(url) =>
      url.startsWith('lib/') ? 'packages/fremad/${url.substring(4)}' : url;
}