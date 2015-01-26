package fremad.domain.user;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonTypeName;


@JsonTypeName( value = "UserMetaObject")
public class UserMetaObject {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Timestamp birthday;
	private String homeTown;
	private String profession;
	
	public UserMetaObject(){
		
	}

	public UserMetaObject(int userId, String firstName, String lastName,
			String phoneNumber, Timestamp birthday, String homeTown,
			String profession) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.homeTown = homeTown;
		this.profession = profession;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	
}
