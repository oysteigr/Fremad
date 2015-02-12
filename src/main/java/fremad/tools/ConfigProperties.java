package fremad.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ConfigProperties {
	 
	private static final Logger LOG = LoggerFactory.getLogger(ConfigProperties.class);
	
	public String getPropValues(String valueName){
 
		Properties prop = new Properties();
		String propFileName = "fremad.properties";
 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
		try{
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		}catch(IOException e){
			LOG.error(e.getMessage());
			return "";
		}
 
 
		// get the property value and print it out
		return prop.getProperty(valueName);
	}
}