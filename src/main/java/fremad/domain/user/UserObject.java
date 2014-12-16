package fremad.domain.user;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonTypeName;


@JsonTypeName( value = "UserObject")
public class UserObject {
	private int id;
	private String userName;
	private String password;
	private String salt;
	private int role;
	private Timestamp created;
	private boolean validated;
	
	public UserObject() {
	}
	public UserObject(int id, String userName, String password, String salt,
			int role, Timestamp created, boolean validated) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.role = role;
		this.created = created;
		this.validated = validated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public UserRoleEnum getRoleEnum() {
		return UserRoleEnum.getUserRoleEnum(role);
	}
}


