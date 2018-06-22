package ar.utn.tacs.model.commons;

import ar.utn.tacs.commons.UtnTacsException;

public class InsufficientCryptoCurrencyException extends UtnTacsException{

	private static final long serialVersionUID = 709955784122883808L;
	
	public InsufficientCryptoCurrencyException() {
		this.defaultMessageError="No posee monto suficiente de la cripto moneda indicada";
	}

}
