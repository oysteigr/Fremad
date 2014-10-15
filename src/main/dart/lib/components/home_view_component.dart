part of fremad;

@Component(
    selector: 'home-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/home_view.html',
    useShadowDom: false
)
class ShowHomeComponent {
  void create() {
    print("In this func");
  }

}