package ar.utn.tacs.model.commons;

import ar.utn.tacs.commons.UtnTacsException;

public class RejectingApprovedDepositException extends UtnTacsException {

	private static final long serialVersionUID = 1000961123889356451L;
	
	public RejectingApprovedDepositException() {
		this.defaultMessageError="Imposible rechazar, el deposito ya fue aprobado";
	}

}
