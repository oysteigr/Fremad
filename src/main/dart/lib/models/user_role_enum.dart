library user_role;

//enum UserRoleEnum {ZERO, SUPPORTER, PLAYER, AUTHOR , EDITOR, ADMIN}

class UserRole{
  int roleValue;
 
  UserRole(this.roleValue);

  Map<String, dynamic> toJson() => <String, dynamic>{
    "roleValue": roleValue
  };

  UserRole.fromJson(Map<String, dynamic> json) : this(json['roleValue']);
  
}