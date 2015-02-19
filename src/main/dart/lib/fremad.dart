library fremad;

import 'dart:html' as html;

import 'dart:async';

import 'dart:convert';

import 'package:angular/angular.dart';


import 'models/article.dart';
import 'models/article_list.dart';
import 'models/table_entry_list.dart';
import 'models/table_entry.dart';
import 'models/team.dart';
import 'models/team_list.dart';
import 'models/league.dart';
import 'models/league_list.dart';
import 'models/player.dart';
import 'models/player_list.dart';
import 'models/match_list.dart';
import 'models/match_entry.dart';
import 'models/user.dart';
import 'models/user_list.dart';
import 'models/user_logon.dart';
import 'models/user_role_enum.dart';
import 'models/user_meta.dart';
import 'models/user_meta_list.dart';
import 'models/role_request.dart';
import 'models/role_request_list.dart';
import 'models/user_login_log.dart';
import 'models/user_login_log_list.dart';


part 'components/header_component.dart';
part 'components/main_menu_component.dart';
part 'components/footer_component.dart';

part 'components/club_view_component.dart';
part 'components/home_view_component.dart';
part 'components/loggin_view_component.dart';
part 'components/sponsors_view_component.dart';
part 'components/team_view_component.dart';
part 'components/admin_view_component.dart';
part 'components/profile_view_component.dart';

part 'components/front/front_article_list_component.dart';
part 'components/front/front_article_component.dart';

part 'components/team/about_view_component.dart';
part 'components/team/players_view_component.dart';
part 'components/team/fixture_view_component.dart';
part 'components/team/table_view_component.dart';

part 'components/admin/articles_view_component.dart';
part 'components/admin/players_view_component.dart';
part 'components/admin/squad_view_component.dart';
part 'components/admin/leagues_view_component.dart';
part 'components/admin/teams_view_component.dart';
part 'components/admin/events_view_component.dart';
part 'components/admin/users_view_component.dart';
part 'components/admin/user_role_view_component.dart';
part 'components/admin/role_request_view_component.dart';
part 'components/admin/user_login_view_component.dart';

part 'components/profile/edit_view_component.dart';
part 'components/profile/validate_view_component.dart';

part 'components/boxes/table_box_component.dart';
part 'components/boxes/match_box_component.dart';

part 'components/utils/loading_util_component.dart';
part 'components/utils/combination_classes_util.dart';
part 'components/utils/date_time_utils.dart';



part 'services/user_handler.dart';
part 'services/messages.dart';

part 'fremad_route_initializer.dart';
part 'global_http_interceptors.dart';

UserHandler USER = new UserHandler(); 

class FremadApp extends Module {
  FremadApp(){
    
    
    bind(UserHandler);
    
    bind(HeaderComponent);
    bind(MainMenuComponent);
    bind(FooterComponent);
    
    bind(ShowTheClubComponent);
    bind(ShowHomeComponent);
    bind(ShowLogginComponent);
    bind(ShowSponsorsComponent);
    bind(ShowTeamComponent);
    bind(ShowAdminComponent);
    bind(ShowProfileComponent);
    
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
    bind(ShowAdminSquadComponent);
    bind(ShowAdminUsersComponent);
    bind(ShowAdminUserRoleComponent);
    bind(ShowAdminRoleRequestComponent);
    bind(ShowAdminUserLoginComponent);
    
    
    bind(ShowProfileEditComponent);
    bind(ShowProfileValidateComponent);
    
    bind(ShowBoxTableComponent);
    bind(ShowBoxMatchComponent);
    
    bind(ShowUtilLoadingComponent);
        
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