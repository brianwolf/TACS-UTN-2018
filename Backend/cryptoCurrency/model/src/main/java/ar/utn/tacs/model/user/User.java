package ar.utn.tacs.model.user;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.wallet.Wallet;

@JsonIgnoreProperties(value = {"id"})
public class User {

	private Long id;
	
	private Login login = new Login();
	
	private Person person = new Person();

	private List<Role> roles = new ArrayList<Role>();
	
	private Wallet wallet = new Wallet();
	
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
		if (this.login == null || other.login == null) {
			return false;
		}
		
		return this.id.equals(other.id) || this.login.equals(other.login);
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

	public boolean canAccessTo(String requestPath) {
		
		return this.roles.stream().anyMatch(r->r.canAccessTo(requestPath));
	}
	
	
}
