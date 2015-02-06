package fremad.domain.user;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName(value = "UserRoleRequestObject")
public class UserRoleRequestObject {
	private int id;
	private int userId;
	private int requestedRole;
	private Timestamp date;
	private boolean accepted;
	private int acceptedByUser;

	
	public UserRoleRequestObject() {
	}
	public UserRoleRequestObject(int id, int userId,
			int requestedRole, Timestamp date, boolean accepted) {
		super();
		this.id = id;
		this.userId = userId;
		this.requestedRole = requestedRole;
		this.date = date;
		this.accepted = accepted;
	}
	
	public UserRoleRequestObject(int id, int userId,
			String requestedRole, Timestamp date, boolean accepted) {
		super();
		this.id = id;
		this.userId = userId;
		this.setRequestedRoleByString(requestedRole);
		this.date = date;
		this.accepted = accepted;
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
	public int getRequestedRole() {
		return requestedRole;
	}
	public void setRequestedRole(int requestedRole) {
		this.requestedRole = requestedRole;
	}
	public void setRequestedRoleByString(String requestedRole) {
		this.requestedRole = UserRoleEnum.valueOf(requestedRole).getRoleValue();
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public int getAcceptedByUser() {
		return acceptedByUser;
	}
	public void setAcceptedByUser(int acceptedByUser) {
		this.acceptedByUser = acceptedByUser;
	}
	
}
