package ar.utn.tacs.model.user;

import java.util.List;

import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.wallet.Wallet;

//ESTO ES SOLO PARA PROBAR HACER UN SELECT
//@NamedNativeQueries({
//		@NamedNativeQuery(name = "findUsers", query = "select * from usuario", resultClass = User.class) })
//@Entity
//@Table(name = "USUARIO")
public class User {

//	@Id
//	@Column(name = "id")
	private Long id;
	
	private Login login;
	
	private Person person;

//	@Transient
	private List<Role> roles = null;
	
//	@Transient
	private Wallet wallet = null;
	
	public User(Long id, Login login, Person person, List<Role> roles, Wallet wallet) {
		this.id = id;
		this.login = login;
		this.person = person;
		this.roles = roles;
		this.wallet = wallet;
	}
	
	public User() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		
		User other = (User) obj;
		return this.id.equals(other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
