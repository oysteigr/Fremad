part of fremad;


@Component(
    selector: 'message-view',
    templateUrl: 'packages/fremad/components/message_view.html',
    cssUrl: 'packages/fremad/components/message_view.css',
    useShadowDom: false
)
class ShowMessageComponent {
  List<Message> messageList = MESSAGE.messageList;

  ShowMessageComponent(){
  }
  
  void removeMessage(Message message){
    MESSAGE.removeMessage(message.id);
  }
}