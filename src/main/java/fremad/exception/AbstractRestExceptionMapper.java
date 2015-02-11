package fremad.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import fremad.domain.RestExceptionStatusInfo;

@Provider 
@Component 
public class AbstractRestExceptionMapper implements ExceptionMapper<AbstractRestException>{
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractRestExceptionMapper.class);
	
	@Override
	public Response toResponse(AbstractRestException e){
		
		LOG.debug("in toResponse");
		
		RestExceptionStatusInfo statusInfo = new RestExceptionStatusInfo(e.getErrorCode(), e.getErrorMessage());
		
		if(statusInfo.getErrorMessage() == null){
			statusInfo.setErrorMessage("Unkown error");
		}
		
		Status status = e.getStatus();
		if(status == null){
			status = Status.BAD_REQUEST;
		}
		
		Response response = Response.status(status).type(MediaType.APPLICATION_JSON).entity(statusInfo).build();
		
		LOG.debug("{}: {}", status.toString(), statusInfo.getErrorMessage());
		
		return response;
		
	}
}
