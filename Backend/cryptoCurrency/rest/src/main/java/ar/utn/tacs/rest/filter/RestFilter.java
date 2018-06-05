package ar.utn.tacs.rest.filter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

import ar.utn.tacs.model.user.User;
import ar.utn.tacs.rest.user.UserRest;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;

public class RestFilter implements ContainerRequestFilter,ContainerResponseFilter {
	
	private static final String USERS_PATH = UserRest.BASE;
	private static final String LOGIN_PATH = USERS_PATH+UserRest.GET_TOKEN_BY_LOGIN;
	private static final String CREATE_USER_PATH = USERS_PATH+UserRest.NEW_USER;
	private static final String TOKEN = "token";
	private static final String RELOG_PATH = USERS_PATH+UserRest.RELOG.replace("/{nick}", "");

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		
//		request.getRequestHeaders().add("Access-Control-Allow-Origin", "*");
//		request.getRequestHeaders().add("Access-Control-Allow-Headers", "Authorization, token, Origin, X-Requested-With, Content-Type");
//		request.getRequestHeaders().add("Access-Control-Expose-Headers", "Location, Content-Disposition");
//		request.getRequestHeaders().add("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS");
//		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
//		request.getRequestHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");			
//		request.getRequestHeaders().add("Access-Control-Allow-Headers", "Content-Type, token");
//		request.getRequestHeaders().add("Access-Control-Expose-Headers", "token");
		
		//ESTO DEBERIA IR ABAJO PARA QUE ANTES SE VALIDE EL TOKEN, PERO X ALGUNA RAZON EN EL OPTIONS NO ME LLEGA EL TOKEN
		if(request.getMethod().equals("OPTIONS")) {
			ResponseBuilder builder = null;
			builder = Response.status(Response.Status.OK);
			throw new WebApplicationException(builder.build());
		}
		
		if(!this.isValid(request)){
	        ResponseBuilder builder = null;
	        builder = Response.status(Response.Status.UNAUTHORIZED);
	        throw new WebApplicationException(builder.build());

	    }
		
		
		return request;
	}
	
	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
		response.getHttpHeaders().add("Access-Control-Allow-Headers", "Authorization, token, Origin, X-Requested-With, Content-Type");
		response.getHttpHeaders().add("Access-Control-Expose-Headers", "Location, Content-Disposition");
		response.getHttpHeaders().add("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS");
		return response;
	}
	
	private boolean isValid(ContainerRequest request) {
		
		if(this.dontNeedToken(request)) {
			return true;
		}
		
		String token = request.getHeaderValue(TOKEN);
		
		User user = obtainUser(token);
		
		return userIsValid(user,request);
	}
	
	private boolean userIsValid(User user, ContainerRequest request) {
		
		if(user!=null) {
			return user.canAccessTo(getRequestPath(request));
		}
		return false;
	}

	private User obtainUser(String token) {
		try {
			return BeanUtil.getBean(UserService.class).getUserByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	private boolean dontNeedToken(ContainerRequest request) {
		
		String requestPath = getRequestPath(request);
		boolean isPOST = request.getMethod().equals("POST");
//		boolean isOPTIONS = request.getMethod().equals("OPTIONS");
		boolean isLoginPath = requestPath.equals(LOGIN_PATH);
		boolean isCreateUserPath = requestPath.equals(CREATE_USER_PATH);
		boolean isRelogPath = requestPath.startsWith(RELOG_PATH);
		
		//ESTA VALIDACION EXISTIRIA SI SE PUDIERA LEER EL TOKEN EN EL OPTIONS
//		return (isLoginPath||isCreateUserPath) && (isPOST||isOPTIONS);
		return (isLoginPath||isCreateUserPath||isRelogPath) && isPOST;
	}

	private String getRequestPath(ContainerRequest request) {
		return "/"+request.getPath();
	}
	
}
