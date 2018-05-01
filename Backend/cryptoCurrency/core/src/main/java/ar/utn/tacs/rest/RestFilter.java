package ar.utn.tacs.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class RestFilter implements ContainerRequestFilter {
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {
//		headers.add("Access-Control-Allow-Origin", "*");
//		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
//		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
//		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
		
		if(!this.satisfactoryValidation(request)){
	        ResponseBuilder builder = null;
	        builder = Response.status(Response.Status.UNAUTHORIZED);
	        throw new WebApplicationException(builder.build());

	    }
		
		return request;
	}
	
	private boolean satisfactoryValidation(ContainerRequest request) {
		
		boolean haveToken = request.getRequestHeaders().containsKey("token");
		
		return haveToken || this.isloginRest(request);
	}
	
	private boolean isloginRest(ContainerRequest request) {
		
		boolean isPOST = request.getMethod().equals("POST");
		boolean isLoginPath = request.getPath().contains("users/login");
		
		return isLoginPath && isPOST;
	}

}
