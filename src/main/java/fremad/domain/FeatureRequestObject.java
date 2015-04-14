package fremad.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FeatureRequestObject")
public class FeatureRequestObject {

	private int id;
	private int userId;
	private String title;
	private String description;
	private Timestamp date;
	private boolean done;
	
	public FeatureRequestObject() {
		super();
	}

	public FeatureRequestObject(int id, int userId, String title,
			String description, Timestamp date, boolean done) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.date = date;
		this.done = done;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}

}
