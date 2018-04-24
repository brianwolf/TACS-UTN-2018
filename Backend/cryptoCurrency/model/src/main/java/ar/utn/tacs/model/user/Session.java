package ar.utn.tacs.model.user;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Session {

	private String hash;
	private Long idUser;
	private DateTimeAtCompleted timeLoginUp;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public DateTimeAtCompleted getTimeLoginUp() {
		return timeLoginUp;
	}

	public void setTimeLoginUp(DateTimeAtCompleted timeLoginUp) {
		this.timeLoginUp = timeLoginUp;
	}
}
