package fremad.domain.user;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserLoginLogObject")
public class UserLoginLogObject {
	private int id;
	private int userId;
	private Timestamp date;
	
	public UserLoginLogObject(){
		
	}
	
	public UserLoginLogObject(int id, int userId, Timestamp date) {
		super();
		this.id = id;
		this.userId = userId;
		this.date = date;
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
	public void setUser_id(int userId) {
		this.userId = userId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
