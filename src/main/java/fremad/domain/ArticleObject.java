package fremad.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class ArticleObject {
	int id;
	int authorId;
	String articleType;
	String header;
	String context;
	String content;
	String imageURL;
	Date date;
}
