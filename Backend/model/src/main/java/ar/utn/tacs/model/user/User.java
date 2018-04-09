package ar.utn.tacs.model.user;

import java.util.List;

import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.wallet.Wallet;

public class User {

	private long id;
	private String nick;
	private String pass;
	private int intentosLogin;
	private boolean activo;
	private List<Role> roles = null;
	private Wallet wallet = null;

	public User(long id, String nick, String pass, int intentosLogin, boolean activo, Wallet wallet, List<Role> listRoles) {
		this.id = id;
		this.nick = nick;
		this.pass = pass;
		this.intentosLogin = intentosLogin;
		this.activo = activo;
		this.wallet = wallet;
		this.roles = listRoles;
	}

	public User() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getIntentosLogin() {
		return intentosLogin;
	}

	public void setIntentosLogin(int intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

}
