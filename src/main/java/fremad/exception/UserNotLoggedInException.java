package fremad.exception;

public class UserNotLoggedInException extends AbstractRestException{

	private static final long serialVersionUID = -8360321841405314191L;

	public UserNotLoggedInException(Exception e, int errorCode,
			String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
