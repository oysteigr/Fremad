package fremad.exception;

public class TechnicalErrorException extends AbstractRestException{

	private static final long serialVersionUID = 8322320114226485183L;

	public TechnicalErrorException(Exception e, int errorCode,
			String errorMessage) {
		super(e, errorCode, errorMessage);
	}

}
