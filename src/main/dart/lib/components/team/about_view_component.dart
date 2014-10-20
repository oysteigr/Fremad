part of fremad;

@Component(
    selector: 'team-about-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/team/about_view.html',
    useShadowDom: false
)
class ShowTeamAboutComponent {
  void create() {
    print("In this func");
  }

}