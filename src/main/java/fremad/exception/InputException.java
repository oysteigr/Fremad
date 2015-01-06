package fremad.exception;

public class InputException extends AbstractRestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -41708038741031981L;

	public InputException(Exception e, int errorCode, String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
