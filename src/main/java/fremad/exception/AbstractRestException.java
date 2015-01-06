package fremad.exception;

import javax.ws.rs.core.Response.Status;

public abstract class AbstractRestException extends RuntimeException{

	private static final long serialVersionUID = -3150249028588447845L;

	
	
	private int errorCode = 0;
	private String errorMessage;
	
	protected Status status;
	
	public AbstractRestException(Exception e, int errorCode, String errorMessage){
		super(e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Status getStatus(){
		return status;
	}
}
