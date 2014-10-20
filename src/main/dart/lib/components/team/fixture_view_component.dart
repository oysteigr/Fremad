part of fremad;

@Component(
    selector: 'team-fixture-view',
    templateUrl: 'packages/fremad/components/team/fixture_view.html',
    useShadowDom: false
)
class ShowTeamFixtureComponent {
  void create() {
    html.window.console.info("Is in ShowTeamFixtureComponent");
  }

}