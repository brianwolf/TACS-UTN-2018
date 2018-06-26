package ar.utn.tacs.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import ar.utn.tacs.commons.UtnTacsException;
import ar.utn.tacs.dao.user.UserDao;
import ar.utn.tacs.model.coin.Coin;
import ar.utn.tacs.model.commons.ExistingUserException;
import ar.utn.tacs.model.commons.UserBlockedException;
import ar.utn.tacs.model.commons.UserNotFoundException;
import ar.utn.tacs.model.deposit.Deposit;
import ar.utn.tacs.model.deposit.DepositRest;
import ar.utn.tacs.model.email.MailBuilder;
import ar.utn.tacs.model.role.AdminRole;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.user.ConnectedUser;
import ar.utn.tacs.model.user.Login;
import ar.utn.tacs.model.user.User;
import ar.utn.tacs.service.admin.AdminService;
import ar.utn.tacs.service.external.ExternalService;
import ar.utn.tacs.service.user.UserService;
import ar.utn.tacs.util.BeanUtil;
import ar.utn.tacs.util.HashUtil;
import ar.utn.tacs.util.TokenMakerUtil;

public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserDao userDao;

	@Override
	public String getTokenByLogin(Login login) throws UtnTacsException {

		String token = "";

		User user = this.userDao.getUserByLogin(login);
		
		if (user == null) {
		User userByNick = this.userDao.getUserByNick(login.getNick());
		
		if(!userByNick.getLogin().getActive()) {
			throw new UserBlockedException();
		}
		
		userByNick.getLogin().incrementTries();
		
		if(userByNick.getLogin().hasExcededTries()) {
			this.blockUser(userByNick);
			throw new UserBlockedException();
		}
		else {
			this.userDao.updateUser(userByNick);
		}

			throw new UserNotFoundException();
		}

		user.getLogin().setLastLogin(new Date());
		this.userDao.updateUser(user);

		token = BeanUtil.getBean(TokenMakerUtil.class).makeToken();
		ConnectedUser connectedUser = new ConnectedUser(token, user.getId());

		this.userDao.deleteConnectedUserById(user.getId());
		this.userDao.insertConnectedUser(connectedUser);

		return token;
	}

	private void blockUser(User user) {
		user.getLogin().setActive(false);
		this.userDao.updateUser(user);
	}

	@Override
	public void newUser(User user) throws UtnTacsException {
		User userBd = this.userDao.getUserByNick(user.getLogin().getNick());

		if (userBd != null) {
			throw new ExistingUserException();
		}

		List<Role> roles = new ArrayList<Role>();
		roles.add(this.userDao.getRolByDescription(Role.USER));

		user.setRoles(roles);
		user.getLogin().setPass(BeanUtil.getBean(HashUtil.class).getStringHash(user.getLogin().getPass()));
		
		this.userDao.insertUser(user);
	}

	@Override
	public void logOutUserByToken(String token) {
		userDao.deleteConnectedUserByToken(token);
	}

	@Override
	public User getUserByToken(String token) throws UtnTacsException {
		
		User user = this.userDao.getUserByToken(token);
		
		if(user==null) {
			throw new UserNotFoundException();
		}
		
		List<Coin> updatedCoins = BeanUtil.getBean(ExternalService.class).coinMarketCap();
		user.getWallet().updateCoinsValue(updatedCoins);
		
		return user;
	}

	@Override
	public User getUserById(ObjectId id) {
		return this.userDao.getUserById(id);
	}

	@Override
	public User getUser(String nick) {
		
		User user = null;
		
		if (!nick.equals("")) {
			user = this.userDao.getUserByNick(nick);
		}
		
		return user;
	}

	@Override
	public List<String> getUsersNickAll() {
		return this.userDao.getUsersNicksAll();
	}

	@Override
	public void declareDeposit(String token, DepositRest depositRest) {
		
		try {
			User user = this.getUserByToken(token);
			Deposit deposit = depositRest.toDeposit(user);
			deposit.setState(Deposit.WAITING);
			
			BeanUtil.getBean(AdminService.class).addDeposit(deposit);
			
		} catch (UtnTacsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void convertUserToAdmin(String nick) throws UserNotFoundException {
		User user = this.userDao.getUserByNick(nick);
		if (user == null) {
			throw new UserNotFoundException();
		}
		
		AdminRole adminRole = (AdminRole) this.userDao.getRolByDescription(Role.ADMIN);

		if (!user.getRoles().contains(adminRole)) {
			user.getRoles().add(adminRole);

			this.userDao.updateUser(user);
		}
	}

	@Override
	public void relogUserByNick(String nick) throws UtnTacsException {
		
		User user = this.userDao.getUserByNick(nick);
		
		if(user==null) {
			throw new UserNotFoundException();
		}
		
		String newPassword = BeanUtil.getBean(HashUtil.class).getRandomHashString();
		
		Boolean sended = BeanUtil.getBean(ExternalService.class).sendMail(MailBuilder.buildRelogMail(user,newPassword));
		
		if(sended) {
			//ESTO ESTA ACA XQ ESTARIA BUENO QUE CUANDO EL TIPO CAMBIA SU PASS NO SE SIGAN USANDO SUS INTENTOS FALLIDOS
			user.getLogin().setTries(0);
			user.getLogin().setActive(true);
			user.getLogin().setPass(BeanUtil.getBean(HashUtil.class).getStringHash(newPassword));
			this.userDao.updateUser(user);
		}
	}

	@Override
	public void changePassword(String token, Login login) throws UtnTacsException {
		
		User user = getUserByToken(token);
		
		if(user.getLogin().getNick()!=login.getNick()) {
			throw new UserNotFoundException();
		}
		
		user.getLogin().setPass(BeanUtil.getBean(HashUtil.class).getStringHash(login.getPass()));
		//ESTO ESTA ACA XQ ESTARIA BUENO QUE CUANDO EL TIPO CAMBIA SU PASS NO SE SIGAN USANDO SUS INTENTOS FALLIDOS
		user.getLogin().setTries(0);
		this.userDao.updateUser(user);
	}

	@Override
	public void updateUser(String token, User oldUser, User newUser) throws UtnTacsException {
		
		User user = this.userDao.getUserByToken(token);
		
		if(user==null) {
			throw new UserNotFoundException();
		}
		
		HashUtil hashUtil = BeanUtil.getBean(HashUtil.class);
		String oldHashedPass = hashUtil.getStringHash(oldUser.getLogin().getPass());
		String userHashedPass = user.getLogin().getPass();
		
		//NEGRADA DE LOS DIOSES
		if(!oldUser.getLogin().getNick().equals(newUser.getLogin().getNick())||!oldUser.getLogin().getPass().equals(userHashedPass)&&!oldHashedPass.equals(userHashedPass)) {
			throw new UserNotFoundException();
		}
		
		String newHashedPass = hashUtil.getStringHash(newUser.getLogin().getPass());
		
		//NEGRADA DE LOS DIOSES
		if(newUser.getLogin().getPass()!=null) {
			
			if(newUser.getLogin().getPass().trim().isEmpty()) {
				throw new UtnTacsException();
			}
			if(!oldUser.getLogin().getPass().equals(newHashedPass)) {
				
				user.getLogin().setPass(newHashedPass);
			}
		}
		
		//NEGRADA DE LOS DIOSES
		if(newUser.getPerson().getEmail()!=null) {
			
			if(!oldUser.getPerson().getEmail().equals(newUser.getPerson().getEmail())) {
				
				user.getPerson().setEmail(newUser.getPerson().getEmail());
			}
		}
		
		this.userDao.updateUser(user);
	}

	@Override
	public void updateConectedUsersInServer(Integer timeInMinutes) {
		userDao.updateConectedUsersInServer(timeInMinutes);
	}

}