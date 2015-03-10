library ng_bind_html_unsafe;

import 'dart:html' as html;
import 'package:angular/angular.dart';

@Directive(
    selector: '[ng-bind-html-unsafe]',
    map: const {'ng-bind-html-unsafe': '=>value'}    
)
    
class NgBindHtmlUnsafeDirective{
  final html.Element element;

  NgBindHtmlUnsafeDirective(this.element);

  set value(value) => element.setInnerHtml(value == null ? '' : value.toString(),
                                             validator: new html.NodeValidatorBuilder()
                                             ..allowHtml5()
                                             ..allowElement('a', attributes: ['href'])
                                             ..allowElement('img', attributes: ['src']));
}