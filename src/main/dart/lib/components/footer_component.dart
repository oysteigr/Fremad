part of fremad;

@Component(
    selector: 'footer-item',
    publishAs: 'ctrl',
    templateUrl: 'packages/fremad/components/footer.html',
    useShadowDom: false
)
class FooterComponent {
  void create() {
    print("In this func");
  }
}