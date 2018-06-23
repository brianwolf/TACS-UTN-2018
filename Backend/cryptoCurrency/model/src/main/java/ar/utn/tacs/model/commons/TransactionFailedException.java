package ar.utn.tacs.model.commons;

import ar.utn.tacs.commons.UtnTacsException;

public class TransactionFailedException extends UtnTacsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2795337626147564048L;
	
	public TransactionFailedException() {
		this.defaultMessageError = "Error en laa ejecucion de la transaccion";
	}

	public TransactionFailedException(String message) {
		this.defaultMessageError = message;
	}

	public static String getCoinChangeErrorMessage(String ticker) {
		return 	"La moneda "+ticker+" ha cambiado su valor en el transcurso de la operacion";
	}

}
