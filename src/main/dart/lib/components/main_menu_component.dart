part of fremad;

@Component(
    selector: 'main-menu',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/main_menu.html',
    useShadowDom: false
)
class MainMenuComponent {
  void create() {
    print("In this func");
  }

}