package ar.utn.tacs.commons;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(value = {"exception"})
public class ResponseExceptionType {

	private Exception exception;
	
	private String message;

	public ResponseExceptionType() {
	}
	
	public ResponseExceptionType(Exception exception, String message) {
		this.exception = exception;
		this.message = message;
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
	
	@JsonProperty(value = "type")
	public String getExceptionName() {
		return this.exception.getClass().getSimpleName();
	}
	
}
