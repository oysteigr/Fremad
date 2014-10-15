part of fremad;

@Injectable()
class Messages {
  RootScope rootScope;

  Messages(this.rootScope);

  void alert(String message){
    rootScope.broadcast("globalAlert", message);
  }
}