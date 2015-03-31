package fremad.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "articleObject")
public class ArticleObject {

	private int id;
	private int authorId;
	private Timestamp date;
	private String articleType;
	private String header;
	private String context;
	private String content;
	private String imageUrl;
	private boolean published;

	public ArticleObject(int id, int authorId, Timestamp date,
			String articleType, String header, String context, String content,
			String imageUrl, boolean published) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.date = date;
		this.articleType = articleType;
		this.header = header;
		this.context = context;
		this.content = content;
		this.imageUrl = imageUrl;
		this.published = published;
	}
	
	public ArticleObject(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}
