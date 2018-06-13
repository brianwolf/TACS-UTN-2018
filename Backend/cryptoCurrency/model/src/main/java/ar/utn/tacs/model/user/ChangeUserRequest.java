package ar.utn.tacs.model.user;

public class ChangeUserRequest {
	
	private User oldUser;
	private User newUser;
	
	public User getOldUser() {
		return oldUser;
	}
	public void setOldUser(User oldUser) {
		this.oldUser = oldUser;
	}
	public User getNewUser() {
		return newUser;
	}
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

}
