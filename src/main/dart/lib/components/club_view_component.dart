part of fremad;

@Component(
    selector: 'club-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/club_view.html',
    useShadowDom: false
)
class ShowTheClubComponent {
  void create() {
    print("In this func");
  }

}