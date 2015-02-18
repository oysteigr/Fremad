package fremad.exception;

public class UserNoPremissisionException extends AbstractRestException{

	private static final long serialVersionUID = 2293362774772300080L;

	public UserNoPremissisionException(Exception e, int errorCode, String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
