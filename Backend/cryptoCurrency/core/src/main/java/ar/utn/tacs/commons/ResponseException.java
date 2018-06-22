package ar.utn.tacs.commons;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(value = {"exception","status"})
public class ResponseException {

	private Exception exception;
	
	private StatusType status = Response.Status.INTERNAL_SERVER_ERROR;
	
	private String message;

	public ResponseException() {
	}
	
	public ResponseException(Exception exception, String message) {
		this.exception = exception;
		this.message = message;
	}
	public ResponseException(Exception exception, String message,StatusType status) {
		this.exception = exception;
		this.message = message;
		this.status = status;
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	@JsonProperty(value = "type")
	public String getExceptionName() {
		return this.exception.getClass().getSimpleName();
	}
	
}
