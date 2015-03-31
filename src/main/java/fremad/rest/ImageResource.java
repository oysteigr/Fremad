package fremad.rest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fremad.domain.ImageObject;
import fremad.processor.ImageProcessor;

@RestController
@Scope("request")
@RequestMapping("/image")
public class ImageResource {
	private static final Logger LOG = LoggerFactory.getLogger(ImageResource.class);
	
	@Autowired
	ImageProcessor imageProcessor;

	@RequestMapping("/saveImage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ImageObject saveImage(@RequestParam("file") MultipartFile file, 
			@RequestParam("title") String title,
			@RequestParam("type") String type, 
			@Context final HttpServletResponse response) {
		LOG.debug("Saving image");
		return imageProcessor.saveImage(file, title, type);
	}

}
