library ng_bind_divelement_html;

import 'package:angular/angular.dart';
import "package:angular/directive/module.dart";
import 'dart:html' as html;


@Directive(
    selector: '[contenteditable=true][ng-model][html]'
)

class ContentEditableDirectiveHtml extends InputTextLike{

  // The implementation is identical to _InputTextlikeDirective but use innerHtml instead of value
  get typedValue => (inputElement as dynamic).text;
  set typedValue(String value) => (inputElement as dynamic).text = (value == null) ? '' : value;

  ContentEditableDirectiveHtml(html.Element inputElement, NgModel ngModel, Scope scope):
    super(inputElement, ngModel, scope,  new NgModelOptions()){
      inputElement
      ..onChange.listen((event) => ngModelOptions.executeChangeFunc(() => processValue(event)))
      ..onInput.listen((event) => ngModelOptions.executeInputFunc(() => processValue(event)))
      ..onBlur.listen((_) => ngModelOptions.executeBlurFunc(() {
        ngModel.markAsTouched();
      }));
}

}