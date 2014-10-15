part of fremad;

@Component(
    selector: 'sponsors-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/sponsors_view.html',
    useShadowDom: false
)
class ShowSponsorsComponent {
  void create() {
    print("In this func");
  }

}