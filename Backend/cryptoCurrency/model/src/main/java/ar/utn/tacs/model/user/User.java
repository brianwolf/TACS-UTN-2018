package ar.utn.tacs.model.user;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

import ar.utn.tacs.dao.impl.MongoPersistentObject;
import ar.utn.tacs.model.role.Role;
import ar.utn.tacs.model.wallet.Wallet;

@Document(collection="users")
@JsonIgnoreProperties(value = {"id"})
public class User extends MongoPersistentObject{

	private Login login = new Login();
	
	private Person person = new Person();

	private List<Role> roles = new ArrayList<Role>();
	
	private Wallet wallet = new Wallet();
	
	public User(Login login, Person person, List<Role> roles, Wallet wallet) {
		super();
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
		
		return getId().equals(other.getId()) || this.login.equals(other.login);
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
