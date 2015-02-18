package fremad.exception;

public class UserPasswordCombiException  extends AbstractRestException{


	private static final long serialVersionUID = -8261497634687223122L;

	public UserPasswordCombiException(Exception e, int errorCode,
			String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
