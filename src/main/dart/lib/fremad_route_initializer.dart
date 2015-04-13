part of fremad;
 
void fremadRouteInitializer(Router router, RouteViewFactory view) {
  html.window.console.info("Is in RouteInit");
  view.configure({
    'home': ngRoute(
      path: '/home',
      viewHtml: '<home-view></home-view>',
      defaultRoute: true
      ),
    'about': ngRoute(
      path: '/about/:articleUrl',
      viewHtml: '<about-view></about-view>'
      ),
    'team': ngRoute(
      path: '/team/:teamId', 
      viewHtml: '<team-view></team-view>',
      mount: {
        "players" : ngRoute(
           path: '/players',
           viewHtml: '<team-players-view></team-players-view>',
           defaultRoute: true
        ),
        "fixture" : ngRoute(
           path: '/fixture',
           viewHtml: '<team-fixture-view></team-fixture-view>'
        ),
        "table" : ngRoute(
           path: '/table',
           viewHtml: '<team-table-view></team-table-view>'
        )
      }),
    'article': ngRoute(
      path: '/article', 
      viewHtml: '<article-view></article-view>',
        mount: {
           "list" : ngRoute(
              path: '/list',
              viewHtml: '<article-list-view></article-list-view>',
              defaultRoute: true
           ),
           "news" : ngRoute(
              path: '/news/:articleId',
              viewHtml: '<article-news-view></article-news-view>'
           ),
           "match" : ngRoute(
              path: '/match/:articleId',
              viewHtml: '<article-match-view></article-match-view>'
           )
         }),
    'loggIn': ngRoute(
      path: '/loggIn', 
      viewHtml: '<loggIn-view></loggIn-view>'
      ),
    'myPage': ngRoute(
      path: '/myPage', 
      viewHtml: '<my-page-view></my-page-view>',
      mount: {
        "edit" : ngRoute(
           path: '/edit',
           viewHtml: '<my-page-edit-view></my-page-edit-view>',
           defaultRoute: true
        ),
        "validate" : ngRoute(
           path: '/validate',
           viewHtml: '<my-page-validate-view></my-page-validate-view>'
        ),
        "resetPassword" : ngRoute(
           path: '/resetPassword',
           viewHtml: '<my-page-reset-password-view></my-page-reset-password-view>'
        ),
        "bugs" : ngRoute(
           path: '/bugs',
           viewHtml: '<my-page-bugs-view></my-page-bugs-view>'
        ),
        "featureRequest" : ngRoute(
           path: '/featureRequest',
           viewHtml: '<my-page-feature-request-view></my-page-feature-request-view>'
        ),
        "developmentPlan" : ngRoute(
           path: '/developmentPlan',
           viewHtml: '<my-page-development-plan-view></my-page-development-plan-view>'
        ),
        "updates" : ngRoute(
           path: '/updates',
           viewHtml: '<my-page-updates-view></my-page-updates-view>'
        )
      }),
    'admin': ngRoute(
      path: '/admin', 
      viewHtml: '<admin-view></admin-view>',
      mount: {
         "news" : ngRoute( 
            path: '/news',
            viewHtml: '<admin-news-view></admin-news-view>',
            defaultRoute: true
         ),
         "matchReports" : ngRoute(
             path: '/matchReports',
             viewHtml: '<admin-match-reports-view></admin-match-reports-view>'
          ),
         "pages" : ngRoute(
            path: '/pages',
            viewHtml: '<admin-pages-view></admin-pages-view>'
         ),
         "teams" : ngRoute(
            path: '/teams',
            viewHtml: '<admin-teams-view></admin-teams-view>'
         ),
         "players" : ngRoute(
            path: '/players',
            viewHtml: '<admin-players-view></admin-players-view>'
         ),
         "squad" : ngRoute(
            path: '/squad',
            viewHtml: '<admin-squad-view></admin-squad-view>'
         ),
         "events" : ngRoute(
            path: '/events',
            viewHtml: '<admin-events-view></admin-events-view>',
            mount: {
              "editevent" : ngRoute(
                path: '/edit/:matchId',
                viewHtml: '<admin-edit-event-view></admin-edit-event-view>'
              )
            }
         ),
         "leagues" : ngRoute(
            path: '/leagues',
            viewHtml: '<admin-leagues-view></admin-leagues-view>'
         ),
         "playerUserRel" : ngRoute(
            path: '/playerUserRel',
            viewHtml: '<admin-player-user-rel-view></admin-player-user-rel-view>'
         ),
         "users" : ngRoute(
            path: '/users',
            viewHtml: '<admin-users-view></admin-users-view>'
         ),
         "userRole" : ngRoute(
            path: '/userRole',
            viewHtml: '<admin-user-role-view></admin-user-role-view>'
         ),
         "roleRequest" : ngRoute(
            path: '/roleRequest',
            viewHtml: '<admin-role-request-view></admin-role-request-view>'
         ),
         "userLogins" : ngRoute(
            path: '/userLogins',
            viewHtml: '<admin-user-login-view></admin-user-login-view>'
         )
      }
    )
  });
}