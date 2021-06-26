package com.magalu.logistica.app.agendamento.api.exception;

public class InfraException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public InfraException(final Throwable cause) {
		super(cause);
	}
	
	public InfraException(final String erro) {
		super(erro);
	}
}
