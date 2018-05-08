package ar.utn.tacs.service.test;

import org.junit.Assert;
import org.junit.Test;

import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public class UserServiceTestSuite extends ServiceTestSuite {
	
	@Test
	public void createUserJesus() {
		String nickJesus = "yisssas";
		User jesus = new User();
		jesus.getLogin().setNick(nickJesus);
		
		userService.newUser(jesus);
		
		Assert.assertNotNull(userService.getUser(nickJesus));
	}
	
	@Test
	public void tostadoSeLoguea() {
		
		String token = getTokenTostado();
		
		Assert.assertNotNull(token);
	}
	
	@Test
	public void tostadoSeDesLoguea() {
		
		Login loginTostado = getLoginTostado();
		
		String token = userService.getTokenByLogin(loginTostado);
		
		userService.logOutUserByToken(token);
		
		Assert.assertNull(userService.getUserByToken(token));
	}
	
	@Test
	public void getTostadoById() {
		
		Assert.assertNotNull(userService.getUserById(getUserTostado().getId()));
	}
	
}
