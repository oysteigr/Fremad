package fremad.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AbstractRestExceptionMapper extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractRestExceptionMapper.class);


	@ExceptionHandler(value = ValidationException.class)
	protected ResponseEntity<Object> handleValidationException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught ValidationException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = "Validation failed";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = UserPasswordCombiException.class)
	protected ResponseEntity<Object> handleUserPasswordCombiException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught UserPasswordCombiException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = "Wrong user and password combination";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = UserNotValidatedException.class)
	protected ResponseEntity<Object> UserNotValidatedException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught UserNotValidatedException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = "User is not validated";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = UserExistsException.class)
	protected ResponseEntity<Object> handleUserExistsException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught UserExistsException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = "This username already exists";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFoundException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught UserNotFoundException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = "No such username";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = UserNoPremissisionException.class)
	protected ResponseEntity<Object> handleUserNoPremissisionException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught UserNoPremissisionException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = ex.getErrorMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = UserNotLoggedInException.class)
	protected ResponseEntity<Object> handleUserNotLoggedInException(AbstractRestException ex, WebRequest request) {
		LOG.debug("Caught UserNotLoggedInException in AbstractRestExceptionMapper");
		
		String bodyOfResponse = ex.getErrorMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	

}
