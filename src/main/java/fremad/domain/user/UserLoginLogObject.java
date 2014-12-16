package fremad.domain.user;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserLoginLogObject")
public class UserLoginLogObject {
	private int id;
	private int user_id;
	private Timestamp date;
	
	public UserLoginLogObject(){
		
	}
	
	public UserLoginLogObject(int id, int user_id, Timestamp date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
