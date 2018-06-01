package ar.utn.tacs.model.deposit;

public enum StateDepositNumber {

	APPROVED("APPROVED"),
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
