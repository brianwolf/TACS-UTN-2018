package ar.utn.tacs.commons;

public class UtnTacsException extends Exception {

	private static final long serialVersionUID = 3997449068912721666L;

	public ResponseExceptionType createBasicResponse(String userMessageError) {
		return new ResponseExceptionType(this, userMessageError);
	}
}
