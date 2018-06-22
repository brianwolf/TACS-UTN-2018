package ar.utn.tacs.rest.commons;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ar.utn.tacs.commons.ResponseException;
import ar.utn.tacs.commons.UtnTacsException;

@Provider
public class UtnTacsExceptionMapper implements ExceptionMapper<UtnTacsException> {

	@Override
	public Response toResponse(UtnTacsException exception) {
		ResponseException responseException = exception.createBasicResponse();
		return Response.status(responseException.getStatus()).entity(responseException).build();
	}

}
