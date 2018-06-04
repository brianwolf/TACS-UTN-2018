package ar.utn.tacs.service.test;

import org.junit.Assert;
import org.junit.Test;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.model.commons.ExistingUserException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;

public class UserServiceTestSuite extends ServiceTestSuite {
	
	@Test
	public void createUserJesus() throws UtnTacsException {
		String nickJesus = "yisssas";
		User jesus = new User();
		jesus.getLogin().setNick(nickJesus);
		
		userService.newUser(jesus);
		
		Assert.assertNotNull(userService.getUser(nickJesus));
	}
	
	@Test(expected=ExistingUserException.class)
	public void intentoCrearATostadoPeroNoPuedoPorqueEsIrremplazable() throws UtnTacsException {
		
		User tostado2 = new User();
		tostado2.setLogin(getLoginTostado());
		
		userService.newUser(tostado2);
	}
	
	@Test
	public void tostadoSeLoguea() {
		
		String token = getTokenTostado();
		
		Assert.assertNotNull(token);
	}
	
	@Test(expected=UserNotFoundException.class)
	public void tostadoSeDesLoguea() throws UtnTacsException {
		
		Login loginTostado = getLoginTostado();
		
		String token = userService.getTokenByLogin(loginTostado);
		
		userService.logOutUserByToken(token);
		
		userService.getUserByToken(token);
	}
	
	@Test
	public void getTostadoById() {
		
		Assert.assertNotNull(userService.getUserById(getUserTostado().getId()));
	}
	
}
