package fremad.domain;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName( value = "UserObject")
public class RestExceptionStatusInfo {
	
	private int errorCode;
	private String errorMessage;
	
	
	public RestExceptionStatusInfo(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
