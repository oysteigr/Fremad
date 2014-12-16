package fremad.domain.user;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName( value = "UserRoleEnum" )
public enum UserRoleEnum {
	UNAUTH (0), 
	SUPPORTER (1), 
	PLAYER (2), 
	AUTHOR (3), 
	EDITOR (4), 
	ADMIN (5);
	
	private int roleValue;
	
	UserRoleEnum(int value){
		this.roleValue = value;
	}
	
	UserRoleEnum(){
		this.roleValue = 0;
	}
	
	public int getRoleValue(){
		return this.roleValue;
	}
	
	public void setRoleValue(int rolevalue){
		this.roleValue = rolevalue;
	}
	
	public static UserRoleEnum getUserRoleEnum(int i) {
		return values[i];
	}
	
	private static UserRoleEnum[] values = UserRoleEnum.values();
	
	
}
