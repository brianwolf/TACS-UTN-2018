package ar.utn.tacs.model.commons;

import ar.utn.tacs.commons.UtnTacsException;

public class UserNotFoundException extends UtnTacsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6694365594798097964L;
	
	public UserNotFoundException() {
		this.defaultMessageError="No existe el deposito solicitado";
	}

}	
