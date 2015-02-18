package fremad.exception;

public class UserNotFoundException  extends AbstractRestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363302187974104721L;

	public UserNotFoundException(Exception e, int errorCode, String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}