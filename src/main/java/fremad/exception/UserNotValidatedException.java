package fremad.exception;

public class UserNotValidatedException  extends AbstractRestException{

	private static final long serialVersionUID = 1193836898382301491L;

	public UserNotValidatedException(Exception e, int errorCode,
			String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
