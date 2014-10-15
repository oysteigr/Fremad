part of fremad;

@Component(
    selector: 'loggin-view',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/loggin_view.html',
    useShadowDom: false
)
class ShowLogginComponent {
  void create() {
    print("In this func");
  }

}