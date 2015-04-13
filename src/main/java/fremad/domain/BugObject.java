package fremad.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BugObject")
public class BugObject {

	private int id;
	private int userId;
	private String title;
	private int priority;
	private String os;
	private String browser;
	private String problem;
	private Timestamp date;
	private boolean fixed;
	
	public BugObject() {
		super();
	}

	public BugObject(int id, int userId, String title, int priority, String os,
			String browser, String problem, Timestamp date, boolean fixed) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.priority = priority;
		this.os = os;
		this.browser = browser;
		this.problem = problem;
		this.date = date;
		this.fixed = fixed;
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public boolean isFixed() {
		return fixed;
	}
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	
}
