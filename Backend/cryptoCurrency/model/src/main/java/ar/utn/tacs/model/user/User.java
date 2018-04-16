package ar.utn.tacs.model.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.wallet.Wallet;

//ESTO ES SOLO PARA PROBAR HACER UN SELECT
@NamedNativeQueries({
		@NamedNativeQuery(name = "findUsers", query = "select * from usuario", resultClass = User.class) })
@Entity
@Table(name = "USUARIO")
public class User {

	@Id
	@Column(name = "id")
	private Long id;
	private String nick;
	private String pass;
	
	@Column(name = "intentos_login")
	private Integer intentosLogin;
	private Boolean activo;
	
	//DEJO PROPERTIES CON ESTE TAG PARA QUE HIBERNATE NO LAS MAPEE
	@Transient
	private List<Role> roles = null;
	
	@Transient
	private Wallet wallet = null;

	public User(Long id, String nick, String pass, Integer intentosLogin, Boolean activo, Wallet wallet, List<Role> listRoles) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getIntentosLogin() {
		return intentosLogin;
	}

	public void setIntentosLogin(Integer intentosLogin) {
		this.intentosLogin = intentosLogin;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
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
