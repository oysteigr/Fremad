part of fremad;

@Injectable()
class UserHandler{
  static final UserHandler _singleton = new UserHandler._internal();
  int _userRole = 0;
  int _userId = -1;
  String _userName = null;

  factory UserHandler() {
    return _singleton;
  }

  UserHandler._internal();
  
  void setUserRole(int userRole){
    this._userRole = userRole;
  }
  
  int getUserRole(){
    return this._userRole;
  }
  
  void setUserId(int userId){
    this._userId = userId;
  }
  
  int getUserId(){
    return this._userId;
  }
  
  void setUserName(String userName){
    this._userName = userName;
  }
  
  String getUserName(int userName){
    return this._userName;
  }
  
  bool checkUserRole(int requiredRole){
    if(requiredRole > this._userRole){
      return false;
    }
    return true;
  }
}