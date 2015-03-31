package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PageObject")
public class PageObject {
	
	private int articleId;
	private String articleTitle;
	private String urlName;
	private int priority;
	private boolean published;
	
	public PageObject(int articleId, String articleTitle, String urlName,
			int priority, boolean published) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.urlName = urlName;
		this.priority = priority;
		this.published = published;
	}

	public PageObject() {
		super();
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
}
