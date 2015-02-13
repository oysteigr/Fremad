part of fremad;
 
void fremadRouteInitializer(Router router, RouteViewFactory view) {
  html.window.console.info("Is in RouteInit");
  view.configure({
    'home': ngRoute(
      path: '/home',
      viewHtml: '<home-view></home-view>',
      defaultRoute: true
      ),
    'theClub': ngRoute(
      path: '/theClub',
      viewHtml: '<club-view></club-view>'
      ),
    'team': ngRoute(
      path: '/team/:teamId', 
      viewHtml: '<team-view></team-view>',
      mount: {
        "about" : ngRoute(
           path: '/about',
           viewHtml: '<team-about-view></team-about-view>',
           defaultRoute: true
        ),
        "players" : ngRoute(
           path: '/players',
           viewHtml: '<team-players-view></team-players-view>'
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
    'sponsors': ngRoute(
      path: '/sponsors', 
      viewHtml: '<sponsors-view></sponsors-view>'
      ),
    'loggIn': ngRoute(
      path: '/loggIn', 
      viewHtml: '<loggIn-view></loggIn-view>'
      ),
    'profile': ngRoute(
      path: '/profile', 
      viewHtml: '<profile-view></profile-view>',
      mount: {
        "edit" : ngRoute(
           path: '/edit',
           viewHtml: '<profile-edit-view></profile-edit-view>',
           defaultRoute: true
        ),
        "validate" : ngRoute(
           path: '/validate',
           viewHtml: '<profile-validate-view></profile-validate-view>'
        )
      }),
    'admin': ngRoute(
      path: '/admin', 
      viewHtml: '<admin-view></admin-view>',
      mount: {
         "articles" : ngRoute(
            path: '/articles',
            viewHtml: '<admin-articles-view></admin-articles-view>',
            defaultRoute: true
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
            viewHtml: '<admin-events-view></admin-events-view>'
         ),
         "leagues" : ngRoute(
            path: '/leagues',
            viewHtml: '<admin-leagues-view></admin-leagues-view>'
         ),
         "users" : ngRoute(
            path: '/users',
            viewHtml: '<admin-users-view></admin-users-view>'
         ),
         "roleRequest" : ngRoute(
            path: '/roleRequest',
            viewHtml: '<admin-role-request-view></admin-role-request-view>'
         )
      }
    )
  });
}