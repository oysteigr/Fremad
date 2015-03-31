part of fremad;

class Message{
  String message;
  String type;
  int id;
  
  Message(this.message, this.type, this.id);
}

@Injectable()
class MessageHandler{
  static final MessageHandler _singleton = new MessageHandler._internal();
  List<Message> messageList = new List<Message>();
  int counter = 0;
  int delay = 4;



  factory MessageHandler() {
    return _singleton;
  }

  MessageHandler._internal();
  
  void _addMessage(String message, String type){
    counter++;
    int id = counter;
    
    this.messageList.add(new Message(message, type, id));
    new Timer(new Duration(seconds: delay),() => removeMessage(id));
  }
  
  void addSuccessMessage(String message){
    this._addMessage(message, "alert-success");
  }
  void addInfoMessage(String message){
    this._addMessage(message, "alert-info");
  }
  void addWarningMessage(String message){
    this._addMessage(message, "alert-warning");
  }
  void addErrorMessage(String message){
    this._addMessage(message, "alert-danger");
  }
  
  void removeMessage(int id){
    messageList.removeWhere((Message) => Message.id == id);
  }
}