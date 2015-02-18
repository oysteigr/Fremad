package fremad.exception;

public class UserExistsException extends AbstractRestException{

	private static final long serialVersionUID = -5849454882230125609L;

	public UserExistsException(Exception e, int errorCode, String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
