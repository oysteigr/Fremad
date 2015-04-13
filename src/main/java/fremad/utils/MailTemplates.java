package fremad.utils;

public class MailTemplates{
	
	final static String VALIDATION_MAIL = ""
			+ "<h2>Validate your account!</h2><br>"
			+ "To activate your account please click on this link: <br>"
			+ "http://drime.no/fremad/#/myPage/validate?[CODE]-[USER_ID] <br>"
			+ "<br>"
			+ "If this account is not activated in 24 hours it will be deleted.<br>"
			+ "<br>"
			+ "Regards, fremadNet";

	final static String FORGOTTEN_PASSWORD_MAIL = ""
			+ "<h2>Have you fotgotten your password?</h2><br>"
			+ "<br>"
			+ "To make sure it so, please click on this link and your new password will be sent to you shortly: <br>"
			+ "http://drime.no/fremad/#/myPage/resetPassword?[CODE]-[USER_ID] <br>"
			+ "<br>"
			+ "This link is active in 24 hours.<br>"
			+ "<br>"
			+ "Regards, fremadNet";
	
	final static String RESET_PASSWORD_MAIL = ""
			+ "<h2>Your password has been reset!</h2><br>"
			+ "<br>"
			+ "Your new password is: <br>"
			+ "<b>[PASSWORD]</b> <br>"
			+ "<br>"
			+ "Please go to your profile page and change your password into your own.<br>"
			+ "<br>"
			+ "Regards, fremadNet";
}
