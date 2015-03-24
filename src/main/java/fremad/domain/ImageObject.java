package fremad.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ImageObject")
public class ImageObject {
	
	private int id;
	private int uploaderId;
	private Timestamp date;
	private String imageType;
	private String title;
	private String Url;
	
	public ImageObject() {
		super();
	}

	public ImageObject(int id, int uploaderId, Timestamp date,
			String imageType, String title, String url) {
		super();
		this.id = id;
		this.uploaderId = uploaderId;
		this.date = date;
		this.imageType = imageType;
		this.title = title;
		Url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(int uploaderId) {
		this.uploaderId = uploaderId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}


