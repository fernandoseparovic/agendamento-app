package com.magalu.logistica.app.agendamento.api.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public BusinessException(final String erro) {
		super(erro);
	}
}
