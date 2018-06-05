package ar.utn.tacs.model.user;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Login {

	private static final Integer MAX_TRIES = 3;

	private String nick;

	private String pass;

	private Boolean active;

	private Integer tries;
	
	private Date lastLogin;
	
	public Login(String nick, String pass, Boolean active, Integer tries) {
		
		this.nick = nick;
		this.pass = pass;
		this.active = active;
		this.tries = tries;
	}
	
	public Login() {
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Login)) {
			return false;
		}

		Login other = (Login) obj;
		return this.nick.equals(other.nick) && this.pass.equals(other.pass);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@JsonIgnore
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getTries() {
		return tries;
	}

	public void setTries(Integer tries) {
		this.tries = tries;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void incrementTries() {
		this.tries+=1;
	}

	public Boolean hasExcededTries() {
		return this.tries>MAX_TRIES;
	}
	
}
