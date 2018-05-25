package ar.utn.tacs.model.admin;

public enum StateDepositNumber {

	APROVATED("APROVATED"),
	REJECTED("REJECTED"),
	WAITING("WAITING");
	
	private String description;
	
	StateDepositNumber(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}
