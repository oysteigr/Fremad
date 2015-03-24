part of fremad;


@Component(
    selector: 'image-handler-editor',
    templateUrl: 'packages/fremad/components/editor/image_handler_editor.html',
    useShadowDom: false
)
class ImageHandlerEditorComp implements ShadowRootAware {
  
    @NgTwoWay('imagePath')
    String path;
    
    @NgTwoWay('useRatio')
    bool fixedRatio;
    
    @NgTwoWay('xRatio')
    int xRatio;
    
    @NgTwoWay('yRatio')
    int yRatio;
    
    @NgTwoWay('minWith')
    int minWith;
    
    @NgTwoWay('minHeight')
    int minHeight;
    
    @NgTwoWay('show')
    bool show;
  
    static final int BOX_SIZE = 50;
    final Http _http;
    
    String title = "";
    
    double ratio;
    double scale;
    
    int minImageWith;
    int minImageHeight;
    
    String errorMessage = "";
  
    html.FormElement _readForm;
    html.InputElement _fileInput;
    
    html.CanvasElement _context;
    html.CanvasElement _image_src;
    html.CanvasElement _overlay;
    
    html.CanvasRenderingContext2D _ctx_image;
    html.CanvasRenderingContext2D _ctx_image_src;
    html.CanvasRenderingContext2D _ctx_overlay;
    
    html.ImageElement _image;
    
    html.Rectangle _global;
    html.Rectangle _local;
    
    int last_x = 0;
    int last_y = 0;
    
    bool isDragging = false;
    
    
    ImageHandlerEditorComp(this._http) {
      //fixedRatio = true;
        
      //ratio =  yRatio / xRatio;
      
     // minImageWith = minWith;
     // minImageHeight = 400;
    }
    
    void onShadowRoot(final html.ShadowRoot shadowRoot) {
      ratio =  yRatio / xRatio;
      
      minImageWith = minWith;
      minImageHeight = minHeight;
      
      _readForm = html.document.querySelector('#read');
      _fileInput = html.document.querySelector('#imageFile');
      _context = html.document.querySelector("#canvasImage");
      _overlay = html.document.querySelector("#canvasOverlay");
      _image_src = new html.CanvasElement();
      
      _fileInput.onChange.listen((e) => _onFileInputChange());

      _overlay.onMouseUp.listen((MouseEvent) => isDragging = false);     
      _overlay.onMouseDown.listen((MouseEvent) => _onMouseDown(MouseEvent));
      _overlay.onMouseMove.listen((MouseEvent) => _onReshape(MouseEvent.client.x, MouseEvent.client.y));
      _overlay.onMouseWheel.listen((WheelEvent) => _onWheel(WheelEvent.deltaY));      
      _overlay.onMouseLeave.listen((MouseEvent) => isDragging = false);
      
      _overlay.onTouchEnd.listen((TouchEvent) => isDragging = false);     
      _overlay.onTouchStart.listen((TouchEvent) => _onTouchStart(TouchEvent));
      _overlay.onTouchMove.listen((TouchEvent) => _onTouchMove(TouchEvent));  
      _overlay.onTouchLeave.listen((TouchEvent) => isDragging = false);

      
      _ctx_image = _context.context2D;
      _ctx_overlay = _overlay.context2D;
      _ctx_image_src = _image_src.context2D;
      

    }

    void _onMouseDown (html.MouseEvent me){
      last_x = me.client.x;
      last_y = me.client.y;
      isDragging = true;
    }
    
    void _onTouchStart(html.TouchEvent te){
      te.preventDefault();

      if (te.touches.length > 0) {
        last_x = te.touches[0].page.x;
        last_y = te.touches[0].page.y;
      }
      isDragging = true;
    }
    
    void  _onTouchMove(html.TouchEvent te){
      te.preventDefault();

      if (te.touches.length > 0) {
        _onReshape(te.touches[0].page.x, te.touches[0].page.y);
      }
    }


    void _onFileInputChange() {
      _onFilesSelected(_fileInput.files.first);
    }

    void _onFilesSelected(html.File file) {
        if (file.type.startsWith('image')) {
          html.FileReader reader = new html.FileReader();
          reader.onLoad.listen((e) {
            errorMessage = "";
            _image = new html.ImageElement(src: reader.result);
            if(_image.width <= minImageWith){
              errorMessage = "Image width shoud be min " + minImageWith.toString() + "px but is " + _image.width.toString() + "px. Image not big enough.";
              return;
            }
            if(_image.height <= minImageHeight){
              errorMessage = "Image height shoud be min " + minImageHeight.toString() + "px but is " + _image.height.toString() + "px. Image not big enough.";
              return;
            }
            _image.onLoad.listen(_onData, onError: _onError, onDone: _onDone, cancelOnError: true);
          });
          reader.readAsDataUrl(file);
        }
        
    }

    void _onData(html.Event e) {
      print("success: ");
      int x = _context.parent.clientWidth;
      int y = (x*_image.height/_image.width).round();
      
      html.ImageData temp = _ctx_image.createImageData(200, 200);
      html.File imageFile;
      
      _ctx_image.canvas.width  = x;
      _ctx_image.canvas.height = y;
      _ctx_image.drawImageScaledFromSource(_image, 0, 0, _image.width, _image.height, 0, 0, x, y);
      _global = _ctx_image.canvas.getBoundingClientRect();
      initImage();
      setRects();
      _ctx_overlay.canvas.style.top = _ctx_image.canvas.style.top;
      _ctx_overlay.canvas.style.left = _ctx_image.canvas.style.left;
      _onResize();
    }
    
    void initImage(){
      scale = _ctx_image.canvas.width / _image.width;
      
      html.window.console.debug("scale: " + scale.toString() + " ctx.canvas.width: " + _ctx_image.canvas.width.toString() + " image.width: " + _image.width.toString());
      
      minWith = (minImageWith * scale).round(); 
      if(fixedRatio){
        minHeight = (minWith * ratio).round();
      }else{
        minHeight = (minImageHeight * scale).round(); 
      }
      
      int x_size = (_ctx_image.canvas.width - 1).round();
      int y_size = (_ctx_image.canvas.height - 1).round();
      if(fixedRatio){
        y_size = (x_size * ratio).round(); 
        if(y_size > _ctx_image.canvas.height){
          y_size = _ctx_image.canvas.height - 1;
          x_size = (y_size / ratio).round(); 
        }
      }
      
      html.window.console.debug("minWith: " + minWith.toString() + " minHeight: " + minHeight.toString());
      
      _ctx_overlay.canvas.width = x_size;
      _ctx_overlay.canvas.height = y_size;
    }

    void _onError(html.Event e) {
      print("error: $e");
    }

    void _onDone() {
      print("done");
    }
    
    void setRects(){
      _global = _ctx_image.canvas.getBoundingClientRect();
      _local = _ctx_overlay.canvas.getBoundingClientRect();
    }
    
    void _onResize(){ 
      
      _ctx_overlay.fillStyle = "rgb(0, 255, 0)";
      _ctx_overlay.globalAlpha = 0.4;
      _ctx_overlay.fillRect(0, 0, _overlay.width, _overlay.height);
      _ctx_overlay.strokeStyle = "rgb(0, 0, 0)";
      _ctx_overlay.strokeRect(0, 0, _overlay.width, _overlay.height);
      _ctx_overlay.strokeRect(0, 0, BOX_SIZE, BOX_SIZE);
      _ctx_overlay.strokeRect(_overlay.width - BOX_SIZE, 0, BOX_SIZE, BOX_SIZE);
      _ctx_overlay.strokeRect(0, _overlay.height - BOX_SIZE, BOX_SIZE, BOX_SIZE);
      _ctx_overlay.strokeRect(_overlay.width - BOX_SIZE, _overlay.height -BOX_SIZE, BOX_SIZE, BOX_SIZE);
    }
    
    void _onReshape(int x, int y){
      if(isDragging){
        
        setRects();
        
        int dx = x - last_x;
        int dy = y - last_y;
        last_x = x;
        last_y = y;
  
        bool inLeft = x  < _local.left + BOX_SIZE;
        bool inRight = x  > _local.right - BOX_SIZE;
        bool inTop = y  < _local.top + BOX_SIZE;
        bool inBottom = y  > _local.bottom - BOX_SIZE;
        
        if(fixedRatio && (inBottom || inTop)){
          num diff = inBottom ? _local.bottom - _global.bottom : _local.top - _global.top;
          if(diff.abs() < 1){
            if(dx > 0 && inRight || dx < 0 && inLeft){
              dx = 0;
            }
          }
        }
        
        int changes = 0;
        
        if((inLeft || inRight) && (inTop || inBottom)){
          
          changes += inLeft ? adjustLeft(dx) : 0;
          changes += inRight ? adjustRight(dx) : 0;
          changes += inTop ? adjustTop(dy) : 0;
          changes += inBottom ? adjustBottom(dy) : 0;

          if(changes > 0){
            _onResize();
          }
          
          return;
        }else{
          
          moveX(dx);
          moveY(dy);
          
          return;
        }
      }
    }
    
    _onWheel(int deltaY){
      setRects();
      
      int changes = 0;
      int D = 5;
      
      int dx =  deltaY > 0 ? D : -D;
      int dy =  deltaY > 0 ? D : -D;
      
      if(fixedRatio){
        num diffTop = _local.top - _global.top;
        num diffBot = _local.bottom - _global.bottom;
        if(diffTop.abs() < D+1 && diffBot.abs() < D+1 && dx < 0){
          dx = 0;
        }
      }
      
      if(fixedRatio){
        dy = (_local.width + dx)*ratio + _local.height;
      }
      
      changes += adjustLeft(dx);
      changes += adjustRight(-dx);
      changes += adjustTop(dy);
      changes += adjustBottom(-dy);
      
      if(changes > 0){
        _onResize();
      }
    }
    
    int moveX(int dx){

      if(_local.left + dx >= _global.left && _global.right >= _local.left + dx + _local.width){
        _ctx_overlay.canvas.style.left = (_local.left + dx).toString() + "px";
        return 1;
      }
      return 0;
    }
    
    int moveY(int dy){
      
      if(_local.top + dy >= _global.top && _global.bottom >= _local.top + dy + _local.height){
        _ctx_overlay.canvas.style.top = (_local.top + dy).toString() + "px";
        return 1;
      }
      return 0;
    }
    
    int adjustLeft(int dx){
      if(_local.left + dx >= _global.left && _local.width - dx >= minWith){
        _ctx_overlay.canvas.style.left = (_local.left + dx).toString() + "px";
        _ctx_overlay.canvas.width = _ctx_overlay.canvas.width - dx;
        return 1;
      }
      return 0;
    }
    
    int adjustRight(int dx){
      if(_local.right + dx <= _global.right && _local.width + dx >= minWith){
        _ctx_overlay.canvas.width = _ctx_overlay.canvas.width + dx;
        return 1;
      }
      return 0;
    }
    
    int adjustTop(int dy){
      if(fixedRatio){
        dy = _ctx_overlay.canvas.height - (_ctx_overlay.canvas.width*ratio).round();
      }
      if(_local.top + dy >= _global.top && _local.height - dy >= minHeight){
        _ctx_overlay.canvas.style.top = (_local.top + dy).toString() + "px";
        _ctx_overlay.canvas.height = _ctx_overlay.canvas.height - dy;
        return 1;
      }
      return 0;
    }
    
    int adjustBottom(int dy){
      if(fixedRatio){
        dy = (_ctx_overlay.canvas.width*ratio).round() - _ctx_overlay.canvas.height;
      }
      if(_local.bottom + dy <= _global.bottom && _local.height + dy >= minHeight){
        _ctx_overlay.canvas.height = _ctx_overlay.canvas.height + dy;
        return 1;
      }
      return 0;
    }
    
    
    void saveImage(){
      drawSrcImage();
      
      String fileType = _fileInput.files.first.type;
      String dataUri = _image_src.toDataUrl(fileType);
      String byteString = html.window.atob(dataUri.split(',')[1]);
      String mimeString = dataUri.split(',')[0].split(':')[1].split(';')[0];
      Uint8List arrayBuffer = new Uint8List(byteString.length);
      Uint8List dataArray = new Uint8List.view(arrayBuffer.buffer);
      for (var i = 0; i < byteString.length; i++) {
        dataArray[i] = byteString.codeUnitAt(i);
      }
      html.Blob blob = new html.Blob([arrayBuffer], fileType);
      
      sendBlob(blob); 
    }
    
    void sendBlob(html.Blob blob){
      final html.HttpRequest req = new html.HttpRequest();
      final html.FormData formData = new html.FormData();

      html.File file = _fileInput.files.first;
      
      formData.appendBlob('file', blob, file.name);
      formData.append('title', title);
      formData.append('type', "FRONT");
 
      req.open("POST", 'rest/image/saveImage');
      req.setRequestHeader('Accept', 'application/json');
      req.send(formData);
      req.onLoad.listen((ProgressEvent) => path = new Image.fromJson(JSON.decode(ProgressEvent.currentTarget.responseText)).url);
      show = false;
      html.window.console.debug(path);
    }
    
    void drawSrcImage(){
      _ctx_image_src.canvas.width  = (_ctx_overlay.canvas.width/scale).round();
      _ctx_image_src.canvas.height = (_ctx_overlay.canvas.height/scale).round();
      int left = (_local.left - _global.left)/scale;
      int top = (_local.top - _global.top)/scale;
      _ctx_image_src.drawImage(_image, -left, -top);
    }
    
}