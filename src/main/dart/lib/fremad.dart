library fremad;

import 'dart:html' as html;
import 'dart:async';
import 'dart:convert';

import 'package:angular/angular.dart';

import 'models/table_entry_list.dart';
import 'models/table_entry.dart';
import 'models/team.dart';
import 'models/team_list.dart';
import 'models/league.dart';
import 'models/league_list.dart';
import 'models/match_list.dart';
import 'models/match_entry.dart';

part 'components/header_component.dart';
part 'components/main_menu_component.dart';
part 'components/footer_component.dart';

part 'components/club_view_component.dart';
part 'components/home_view_component.dart';
part 'components/loggin_view_component.dart';
part 'components/sponsors_view_component.dart';
part 'components/team_view_component.dart';
part 'components/admin_view_component.dart';

part 'components/front/front_article_list_component.dart';
part 'components/front/front_article_component.dart';

part 'components/team/about_view_component.dart';
part 'components/team/players_view_component.dart';
part 'components/team/fixture_view_component.dart';
part 'components/team/table_view_component.dart';

part 'components/admin/articles_view_component.dart';
part 'components/admin/players_view_component.dart';
part 'components/admin/leagues_view_component.dart';
part 'components/admin/teams_view_component.dart';
part 'components/admin/events_view_component.dart';

part 'components/boxes/table_box_component.dart';

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
    bind(ShowAdminComponent);
    
    bind(FrontArticleComponent);
    bind(FrontArticleListComponent);
    
    bind(ShowTeamAboutComponent);
    bind(ShowTeamPlayersComponent);
    bind(ShowTeamFixtureComponent);
    bind(ShowTeamTableComponent);
    
    bind(ShowAdminArticlesComponent);
    bind(ShowAdminTeamsComponent);
    bind(ShowAdminLeaguesComponent);
    bind(ShowAdminEventsComponent);
    bind(ShowAdminPlayersComponent);
    
    bind(ShowBoxTableComponent);
    
    bind(Messages);
    
    bind(RouteInitializerFn, toValue: fremadRouteInitializer);
    bind(NgRoutingUsePushState, toValue: new NgRoutingUsePushState.value(false));

//    bind(UrlRewriter, toImplementation: FremadUrlRewriter);
  }
}


@Injectable()
class FremadUrlRewriter implements UrlRewriter {
  String call(url) =>
      url.startsWith('lib/') ? 'packages/fremad/${url.substring(4)}' : url;
}