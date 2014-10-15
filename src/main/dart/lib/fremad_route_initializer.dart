part of fremad;
 
void fremadRouteInitializer(Router router, RouteViewFactory view) {
  html.window.console.error("Is in RouteInit");
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
      viewHtml: '<team-view></team-view>'
      ),
    'sponsors': ngRoute(
      path: '/sponsors', 
      viewHtml: '<sponsors-view></sponsors-view>'
      ),
    'loggIn': ngRoute(
      path: '/loggIn', 
      viewHtml: '<loggIn-view></loggIn-view>'
      )
  });
}