package fremad.domain.user;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserRoleRequestObject")
public class UserRoleRequestObject {
	private int id;
	private int userId;
	private UserRoleEnum requestedRole;
	private Timestamp date;
	private boolean validated;
	
	
	public UserRoleRequestObject() {
		super();
	}
	public UserRoleRequestObject(int id, int userId,
			UserRoleEnum requestedRole, Timestamp date, boolean validated) {
		super();
		this.id = id;
		this.userId = userId;
		this.requestedRole = requestedRole;
		this.date = date;
		this.validated = validated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserRoleEnum getRequestedRole() {
		return requestedRole;
	}
	public void setRequestedRole(UserRoleEnum requestedRole) {
		this.requestedRole = requestedRole;
	}
	public void setRequestedRole(String requestedRole) {
		this.requestedRole = UserRoleEnum.valueOf(requestedRole);
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
}
