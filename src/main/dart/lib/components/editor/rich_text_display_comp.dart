part of fremad;


@Component(
    selector: 'rich-text-display',
    templateUrl: 'packages/fremad/components/editor/rich_text_display.html',
    useShadowDom: false
)
class RichTextDisplayComp{
  
  @NgTwoWay('textSource')
  String text;
   
  DirectiveMap _directives;
  final html.Element _element;
  
  
  RichTextDisplayComp(this._element, this._directives){
    
      html.window.console.debug(text);

  }
    
}