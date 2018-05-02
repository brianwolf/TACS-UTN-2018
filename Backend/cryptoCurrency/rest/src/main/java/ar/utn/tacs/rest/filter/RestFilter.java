package ar.utn.tacs.rest.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import ar.utn.tacs.rest.user.UserRest;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;

public class RestFilter implements ContainerRequestFilter {
	
	private static final String USERS_PATH = UserRest.BASE;
	private static final String LOGIN_PATH = USERS_PATH+UserRest.GET_TOKEN_BY_LOGIN;
	private static final String CREATE_USER_PATH = USERS_PATH+UserRest.NEW_USER;
	private static final String TOKEN = "token";

	@Override
	public ContainerRequest filter(ContainerRequest request) {
//		headers.add("Access-Control-Allow-Origin", "*");
//		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
//		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
//		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
		
		if(!this.isValid(request)){
	        ResponseBuilder builder = null;
	        builder = Response.status(Response.Status.UNAUTHORIZED);
	        throw new WebApplicationException(builder.build());

	    }
		
		return request;
	}
	
	private boolean isValid(ContainerRequest request) {
		
		if(this.dontNeedToken(request)) {
			return true;
		}
		
		String token = request.getHeaderValue(TOKEN);
		
<<<<<<< HEAD:Backend/cryptoCurrency/rest/src/main/java/ar/utn/tacs/rest/filter/RestFilter.java
		return BeanUtil.getBean(UserService.class).getUserByToken(token)!=null;
=======
		return haveToken || this.isloginRest(request) || this.isNewUserRest(request);
>>>>>>> master:Backend/cryptoCurrency/core/src/main/java/ar/utn/tacs/rest/RestFilter.java
	}
	
	private boolean dontNeedToken(ContainerRequest request) {
		
		String requestPath = "/"+request.getPath();
		boolean isPOST = request.getMethod().equals("POST");
		boolean isLoginPath = requestPath.equals(LOGIN_PATH);
		boolean isCreateUserPath = requestPath.equals(CREATE_USER_PATH);
		
		return (isLoginPath||isCreateUserPath) && isPOST;
	}
	
	private boolean isNewUserRest(ContainerRequest request) {
		
		boolean isPOST = request.getMethod().equals("POST");
		boolean isLoginPath = request.getPath().contains("users");
		
		return isLoginPath && isPOST;
	}

}
