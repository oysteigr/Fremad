package fremad.exception;

public class ValidationException extends AbstractRestException{


	private static final long serialVersionUID = -3624887153050983329L;


	public ValidationException(Exception e, int errorCode, String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
