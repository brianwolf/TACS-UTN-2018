package ar.utn.tacs.model.commons;

import ar.utn.tacs.commons.UtnTacsException;

public class ExistingDepositException extends UtnTacsException {

	private static final long serialVersionUID = -5909864020530915549L;
	
	public ExistingDepositException() {
		this.defaultMessageError="Ya existe un deposito con ese numero";
	}
	
}
