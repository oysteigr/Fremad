library ng_bind_divelement_rich;

import 'package:angular/angular.dart';
import "package:angular/directive/module.dart";
import 'dart:html' as html;


@Directive(
    selector: '[contenteditable=true][ng-model][rich]'
)

class ContentEditableDirectiveRich extends InputTextLike{

  // The implementation is identical to _InputTextlikeDirective but use innerHtml instead of value
  get typedValue => (inputElement as dynamic).innerHtml;
  set typedValue(String value) => (inputElement as dynamic).setInnerHtml(value == null ? '' : value, 
                                                validator: new html.NodeValidatorBuilder()
                                                 ..allowHtml5()
                                                 ..allowElement('a', attributes: ['href'])
                                                 ..allowElement('img', attributes: ['src']));
  
  ContentEditableDirectiveRich(html.Element inputElement, NgModel ngModel, Scope scope):
      super(inputElement, ngModel, scope,  new NgModelOptions()){
        inputElement
        ..onChange.listen((event) => ngModelOptions.executeChangeFunc(() => processValue(event)))
        ..onInput.listen((event) => ngModelOptions.executeInputFunc(() => processValue(event)))
        ..onBlur.listen((_) => ngModelOptions.executeBlurFunc(() {
          ngModel.markAsTouched();
        }));
  }
  

}
