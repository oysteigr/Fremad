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
      path: '/team', 
      viewHtml: '<team-view></team-view>',
      mount: {
        "about" : ngRoute(
           path: '/:teamId/about',
           viewHtml: '<team-about-view></team-about-view>',
           defaultRoute: true
        ),
        "players" : ngRoute(
           path: '/:teamId/players',
           viewHtml: '<team-players-view></team-players-view>'
        ),
        "fixture" : ngRoute(
           path: '/:teamId/fixture',
           viewHtml: '<team-fixture-view></team-fixture-view>'
        ),
        "table" : ngRoute(
           path: '/:teamId/table',
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
         "events" : ngRoute(
            path: '/events',
            viewHtml: '<admin-events-view></admin-events-view>'
         ),
         "leagues" : ngRoute(
            path: '/leagues',
            viewHtml: '<admin-leagues-view></admin-leagues-view>'
         )
      }
    )
  });
}