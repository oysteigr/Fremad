package fremad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fremad.dao.JdbcImageDao;
import fremad.domain.ImageObject;

@Service
public class ImageService {
	
	@Autowired
	private JdbcImageDao imageDao;

	public ImageObject addImage(ImageObject image) {
		return imageDao.addImage(image);
		
	}

}
