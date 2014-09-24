package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArticleObject {
	int id;
	int articleType;
	String header;
	String context;
	String content;
	String imageURL;
}
