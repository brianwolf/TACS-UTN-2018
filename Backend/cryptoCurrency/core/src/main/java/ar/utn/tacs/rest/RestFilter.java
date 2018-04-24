package ar.utn.tacs.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class RestFilter implements ContainerRequestFilter {
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		MultivaluedMap<String, String> headers = request.getRequestHeaders();
//		headers.add("Access-Control-Allow-Origin", "*");
//		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
//		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
//		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
		
		if(!headers.containsKey("token")){
	        ResponseBuilder builder = null;
	        builder = Response.status(Response.Status.UNAUTHORIZED);
	        throw new WebApplicationException(builder.build());

	    }
		
		return request;
	}

}
