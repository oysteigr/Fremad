part of fremad;

@Component(
    selector: 'header-item',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/header.html',
    useShadowDom: false
)
class HeaderComponent {
  void create() {
    print("In this func");
  }

}