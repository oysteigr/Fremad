part of fremad;


@Component(
    selector: 'rich-text-editor', 
    templateUrl: 'packages/fremad/components/editor/rich_text_editor.html', 
    useShadowDom: false
    )

class RichTextEditorComp implements ShadowRootAware {

  @NgTwoWay('textSource')
  String text;

  bool isHtml = false;
  html.DivElement _textbox_html;
  html.DivElement _textbox_rich;
  html.DivElement _toolbar_html;
  html.DivElement _toolbar_rich;
  html.DivElement _textEditorContainer;
  html.TextAreaElement _rawTextArea;
  html.DivElement _container_button_rich;
  html.DivElement _container_button_html;

  bool showSource = false;

  DirectiveMap _directives;

  get commonAncNode => html.window.getSelection().getRangeAt(0).commonAncestorContainer;

  RichTextEditorComp(this._element, this._directives) {



  }

  final html.Element _element;

  void onShadowRoot(final html.ShadowRoot shadowRoot) {
    this._container_button_rich = _element.querySelector('#rich-button-container');
    this._container_button_html = _element.querySelector('#html-button-container');
    this._container_button_rich.children.add(_buildRichToolbar());
    this._container_button_html.children.add(_buildHtmlToolbar());
    this._textbox_html = _element.querySelector('#text-editor-html');
    this._textbox_rich = _element.querySelector('#text-editor-rich');
  }

  _buildRichToolbar() {
    _toolbar_rich = new html.DivElement();
    _toolbar_rich.classes.add("toolbar");

    _toolbar_rich.children.add(_createButton("B", _toggleFormat, "bold"));
    _toolbar_rich.children.add(_createButton("I", _toggleFormat, "italic"));
    _toolbar_rich.children.add(_createButton("U", _toggleFormat, "underline"));
    _toolbar_rich.children.add(_getSeparator());
    _toolbar_rich.children.add(_createButton("h2", _toggleTag, "h2"));
    _toolbar_rich.children.add(_createButton("h3", _toggleTag, "h3"));
    _toolbar_rich.children.add(_createButton("h4", _toggleTag, "h4"));
    _toolbar_rich.children.add(_createButton("<del>hX</del>", _toggleTag, "div"));
    _toolbar_rich.children.add(_getSeparator());   
    _toolbar_rich.children.add(_createButton("Ordered List", _toggleFormat, "insertorderedlist"));
    _toolbar_rich.children.add(_createButton("Unordered List", _toggleFormat, "insertunorderedlist"));
    _toolbar_rich.children.add(_getSeparator());
    _toolbar_rich.children.add(_createButton("Source", _sourceThis, ""));
    return _toolbar_rich;
  }

  _buildHtmlToolbar() {
    _toolbar_html = new html.DivElement();
    _toolbar_html.classes.add("toolbar");

    _toolbar_html.children.add(_createButton("Visual", _sourceThis, ""));
    return _toolbar_html;
  }

  _createButton(String label, clickFunction, String syntax) {
    var button = new html.ButtonElement();
    button.innerHtml = label;
    button.onClick.listen((e) => clickFunction(e, syntax));
    button.classes.add("editor-btn");
    button.classes.add("blue");

    return button;
  }

  _getSeparator() {
    return new html.Element.html("<span>&nbsp;&nbsp;</span>");
  }

  void _sourceThis(e, String syntax) {
    html.window.console.info("in _sourceThis");
    showSource = !showSource;
  }

  void _debugThis(e, String syntax) {
    String startTag = "<" + syntax + ">";
    String endTag = "</" + syntax + ">";

    html.Range range = html.window.getSelection().getRangeAt(0);



    if (range.startContainer == range.endContainer) {
      html.window.console.info("same container");
    }

    html.DocumentFragment dokFrag = new html.DocumentFragment();
    dokFrag = html.window.getSelection().getRangeAt(0).cloneContents();
    html.window.console.info(dokFrag.innerHtml);
    html.window.console.debug(html.window.getSelection());
    html.window.console.debug(html.window.getSelection().getRangeAt(0));

  }

  void _toggleFormatOld(e, String syntax) {
    String startTag = "<" + syntax + ">";
    String endTag = "</" + syntax + ">";

    html.Range range = html.window.getSelection().getRangeAt(0);

    html.window.console.debug(range);


    if (range.startContainer == range.endContainer) {
      html.window.console.info("same container");
    }

    html.DocumentFragment dokFrag = new html.DocumentFragment();
    dokFrag = html.window.getSelection().getRangeAt(0).extractContents();
    html.window.console.info(dokFrag.innerHtml);
    html.window.console.debug(html.window.getSelection());
    html.window.console.debug(html.window.getSelection().getRangeAt(0));
    String innertext = dokFrag.innerHtml;
    if (_isElementOf(syntax, commonAncNode.parent)) {
      _trimDownToElement(syntax, commonAncNode.parent);
      html.window.console.info("inside " + startTag);
      range.insertNode(dokFrag);
      html.Node ancNode = commonAncNode;

      while (ancNode.childNodes.length > 0) {
        html.window.console.info(ancNode.childNodes.first.text + " = " + innertext);
        if (ancNode.childNodes.first.text != innertext) {
          html.DocumentFragment newNode = new html.DocumentFragment();
          newNode.innerHtml = startTag + ancNode.childNodes.first.nodeValue + endTag;
          html.window.console.debug(newNode);
          ancNode.insertBefore(newNode, ancNode.firstChild);
          ancNode.firstChild.nextNode.remove();
        }
        commonAncNode.parentNode.insertBefore(ancNode.childNodes.first, ancNode);
      }
      commonAncNode.remove();
    } else {
      html.window.console.info("not inside " + startTag);
      dokFrag.innerHtml = startTag + dokFrag.innerHtml.toString() + endTag;
      range.insertNode(dokFrag);
    }
    html.window.console.info(text);
    _textbox_rich.dispatchEvent(new html.Event.eventType('TextEvent', 'change'));
  }

  _toggleFormat(e, String syntax) {
    _textbox_rich.ownerDocument.execCommand(syntax,true,"");
  }
  
  _toggleTag(e, String syntax) {
    _textbox_rich.ownerDocument.execCommand("formatBlock",false,syntax);
  }

  
  _toggleBold(e) => _textbox_rich.ownerDocument.execCommand("bold",true,"");
  _toggleItalic(e) => _textbox_rich.ownerDocument.execCommand("italic",true,"");
  _toggleUnderline(e) => _textbox_rich.ownerDocument.execCommand("underline",true,"");
  
  _leftJustify(e) => _textbox_rich.ownerDocument.execCommand("justifyleft",true,"");
  _centerJustify(e) => _textbox_rich.ownerDocument.execCommand("justifycenter",true,"");
  _rightJustify(e) => _textbox_rich.ownerDocument.execCommand("justifyright",true,"");
  
  _orderedList(e) => _textbox_rich.ownerDocument.execCommand("insertorderedlist",true,"");
  _unorderedList(e) => _textbox_rich.ownerDocument.execCommand("insertunorderedlist",true,"");

  bool _isElementOf(String tagName, html.Element parent) {
    html.window.console.info("checking element of <i>");
    html.Element current = parent;
    html.HtmlElement test;
    while (current.localName != 'div') {
      if (current.localName == tagName) {
        return true;
      }
      current = current.parent;
    }
    return false;
  }

  _trimDownToElement(String tagName, html.Element parent) {

  }

  String getMode() {
    return showSource ? "html" : "rich";
  }
}
