package fremad.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileHandler {
	private static final Logger LOG = LoggerFactory.getLogger(FileHandler.class);
	
	static String basePath = System.getProperty("user.home") + "/fremadUploads/images/";
	
	public static String saveMultiparFile(MultipartFile file){

		Date today =  new Date();
		String localPath = new SimpleDateFormat("YYYY/MM/").format(today); 
		String savePath = basePath + localPath;
		String fileName = "" + today.getTime();
		
		String filetype = FilenameUtils.getExtension(file.getOriginalFilename());
		fileName = fileName + "." + filetype;
		
		createFoldersIfNeeded(savePath);
		
		if (!file.isEmpty()) {
			try {
			byte[] bytes = file.getBytes();	
			LOG.debug("Filename: " + file.getOriginalFilename() + " Type: "
					+ file.getContentType() + " Size: " + file.getSize());
			FileOutputStream out;
				out = new FileOutputStream(savePath + fileName);
				out.write(bytes);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// store the bytes somewhere
		} else {
			
		}
		return "uploads/" + localPath + fileName;
	}
	
	private static void createFoldersIfNeeded(String path){
		File files = new File(path);
		if (!files.exists()) {
			if (files.mkdirs()) {
				LOG.debug("Multiple directories are created!");
			} else {
				LOG.debug("Failed to create multiple directories!");
			}
		}
	}
}
