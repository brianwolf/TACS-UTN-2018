package ar.utn.tacs.service.test;

import java.math.BigDecimal;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.Person;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.model.wallet.Wallet;
import ar.utn.tacs.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {"/applicationContextTest.xml"})
public abstract class ServiceTestSuite {
	
	@Autowired
	UserService userService;
	
	protected Login getLoginTostado() {
		return new Login("tostado", "1234", true, 0);
	}
	
	protected User getUserTostado() {
		return new User(2l, new Login("tostado", "1234", true, 0), new Person("alexis", "taberna", "tostado@gmail.com"), null, new Wallet(null, new BigDecimal(10000f)));
	}
	protected User getUserTostadoPosta() {
		return userService.getUserById(getUserTostado().getId());
	}
	
	protected String getTokenTostado() {
		Login loginTostado = getLoginTostado();
		
		return userService.getTokenByLogin(loginTostado);
	}

}
