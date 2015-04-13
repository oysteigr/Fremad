library fremad;

import 'dart:html' as html;
import 'dart:async';
import 'dart:convert';
import 'dart:typed_data';

import 'package:angular/angular.dart';
import 'package:browser_detect/browser_detect.dart';

import 'directives/ng_bind_html_unsafe.dart';
import 'directives/ng_bind_divelement_html.dart';
import 'directives/ng_bind_divelement_rich.dart';


import 'models/image.dart';
import 'models/bug.dart';
import 'models/article.dart';
import 'models/article_list.dart';
import 'models/page.dart';
import 'models/page_list.dart';
import 'models/table_entry_list.dart';
import 'models/table_entry.dart';
import 'models/team.dart';
import 'models/team_list.dart';
import 'models/league.dart';
import 'models/league_list.dart';
import 'models/player.dart';
import 'models/event.dart';
import 'models/event_list.dart';
import 'models/player_list.dart';
import 'models/match_list.dart';
import 'models/match_entry.dart';
import 'models/match_report.dart';
import 'models/match_report_list.dart';
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
import 'models/player_note.dart';
import 'models/player_note_list.dart';

part 'components/editor/rich_text_editor_comp.dart';
part 'components/editor/rich_text_display_comp.dart';
part 'components/editor/image_handler_editor_comp.dart';

part 'components/header_component.dart';
part 'components/main_menu_component.dart';
part 'components/footer_component.dart';

part 'components/about_view_component.dart';
part 'components/home_view_component.dart';
part 'components/loggin_view_component.dart';
part 'components/article_view_component.dart';
part 'components/team_view_component.dart';
part 'components/admin_view_component.dart';
part 'components/my_page_view_component.dart';
part 'components/message_view_component.dart';


part 'components/front/front_article_list_component.dart';
part 'components/front/front_article_component.dart';

part 'components/team/players_view_component.dart';
part 'components/team/fixture_view_component.dart';
part 'components/team/table_view_component.dart';

part 'components/article/article_list_component.dart';
part 'components/article/article_news_component.dart';
part 'components/article/article_match_component.dart';

part 'components/admin/news_view_component.dart';
part 'components/admin/match_reports_view_component.dart';
part 'components/admin/pages_view_component.dart';
part 'components/admin/players_view_component.dart';
part 'components/admin/player_user_rel_view_component.dart';
part 'components/admin/squad_view_component.dart';
part 'components/admin/leagues_view_component.dart';
part 'components/admin/teams_view_component.dart';
part 'components/admin/events_view_component.dart';
part 'components/admin/edit_event_view_component.dart';
part 'components/admin/users_view_component.dart';
part 'components/admin/user_role_view_component.dart';
part 'components/admin/role_request_view_component.dart';
part 'components/admin/user_login_view_component.dart';

part 'components/my_page/edit_view_component.dart';
part 'components/my_page/validate_view_component.dart';
part 'components/my_page/reset_password_view_component.dart';
part 'components/my_page/bugs_view_component.dart';
part 'components/my_page/development_plan_view_component.dart';
part 'components/my_page/feature_request_view_component.dart';
part 'components/my_page/updates_view_component.dart';

part 'components/boxes/table_box_component.dart';
part 'components/boxes/match_box_component.dart';

part 'components/utils/loading_util_component.dart';
part 'components/utils/user_combination_classes_util.dart';
part 'components/utils/player_combination_classes_util.dart';
part 'components/utils/date_time_utils.dart';
part 'components/utils/general_support_classes_util.dart';



part 'services/user_handler.dart';
part 'services/message_handler.dart';

part 'fremad_route_initializer.dart';

UserHandler USER = new UserHandler();
MessageHandler MESSAGE = new MessageHandler(); 

class FremadApp extends Module {
  FremadApp(){
    
    
    bind(UserHandler);
    bind(MessageHandler);
    
    bind(RichTextEditorComp);
    bind(RichTextDisplayComp);
    bind(ImageHandlerEditorComp);
    
    bind(NgBindHtmlUnsafeDirective);
    bind(ContentEditableDirectiveHtml);
    bind(ContentEditableDirectiveRich);
    
    bind(HeaderComponent);
    bind(MainMenuComponent);
    bind(FooterComponent);
    
    bind(ShowTheClubComponent);
    bind(ShowHomeComponent);
    bind(ShowLogginComponent);
    bind(ShowArticleComponent);
    bind(ShowTeamComponent);
    bind(ShowAdminComponent);
    bind(ShowMyPageComponent);
    bind(ShowMessageComponent);
    
    bind(FrontArticleComponent);
    bind(FrontArticleListComponent);
    
    bind(ShowTeamPlayersComponent);
    bind(ShowTeamFixtureComponent);
    bind(ShowTeamTableComponent);
    
    bind(ShowArticleListComponent);
    bind(ShowArticleNewsComponent);
    bind(ShowArticleMatchComponent);
    
    bind(ShowAdminNewsComponent);
    bind(ShowAdminMatchReportsComponent);
    bind(ShowAdminPagesComponent);
    bind(ShowAdminTeamsComponent);
    bind(ShowAdminLeaguesComponent);
    bind(ShowAdminEventsComponent);
    bind(ShowAdminEditEventComponent);
    bind(ShowAdminPlayersComponent);
    bind(ShowAdminPlayerUserRelComponent);
    bind(ShowAdminSquadComponent);
    bind(ShowAdminUsersComponent);
    bind(ShowAdminUserRoleComponent);
    bind(ShowAdminRoleRequestComponent);
    bind(ShowAdminUserLoginComponent);
    
    
    bind(ShowMyPageEditComponent);
    bind(ShowMyPageValidateComponent);
    bind(ShowMyPageResetPasswordComponent);
    bind(ShowMyPageBugsComponent);
    bind(ShowMyPageDevelopmentPlanComponent);
    bind(ShowMyPageFeatureRequestComponent);
    bind(ShowMyPageUpdatesComponent);
    
    bind(ShowBoxTableComponent);
    bind(ShowBoxMatchComponent);
    
    bind(ShowUtilLoadingComponent);
        
    
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