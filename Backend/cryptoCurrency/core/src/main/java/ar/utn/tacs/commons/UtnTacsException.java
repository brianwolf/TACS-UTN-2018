package ar.utn.tacs.commons;

public class UtnTacsException extends Exception {

	private static final long serialVersionUID = 3997449068912721666L;
	protected String defaultMessageError="";

	public ResponseException createBasicResponse(String userMessageError) {
		return new ResponseException(this, userMessageError);
	}
	public ResponseException createBasicResponse() {
		return new ResponseException(this, defaultMessageError);
	}
}
