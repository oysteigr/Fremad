package fremad.processor;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import fremad.domain.ImageObject;
import fremad.domain.user.UserRoleEnum;
import fremad.security.SessionSecurityContext;
import fremad.service.ImageService;
import fremad.utils.FileHandler;


@Component
@Scope("request")
public class ImageProcessor {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private SessionSecurityContext securityContext;
	
	
	public ImageObject saveImage(MultipartFile file, String title, String type){
		
		securityContext.checkUserPremission(UserRoleEnum.EDITOR);
		
		String path = FileHandler.saveMultiparFile(file);
		ImageObject image = new ImageObject(-1, securityContext.getUserId(), new Timestamp(new Date().getTime()), type, title, path);
		image = imageService.addImage(image);
		
		return image;
	}
}
