package fremad.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MailGenerator extends MailTemplates{
	
	public static String generateValidationMail(Integer userId, String code){
		
		Map<String,String> stringMap = new HashMap<String, String>();
		stringMap.put("[CODE]", code);
		stringMap.put("[USER_ID]", userId.toString());
		
		return fillValues(VALIDATION_MAIL, stringMap);
	}
	
	private static String fillValues(String template, Map<String, String> stringMap){
		Set<String> keySet = stringMap.keySet();
		for (String key : keySet){
			template = template.replace(key, stringMap.get(key));
		}
			
		return template;
	}
}
