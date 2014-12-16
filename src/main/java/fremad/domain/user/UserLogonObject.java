package fremad.domain.user;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName(value = "UserLogonObject")
public class UserLogonObject {

	private String userName;
	private String password;

	public UserLogonObject() {
	}
	public UserLogonObject(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
