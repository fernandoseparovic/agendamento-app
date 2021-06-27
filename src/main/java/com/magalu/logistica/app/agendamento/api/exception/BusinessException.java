package com.magalu.logistica.app.agendamento.api.exception;

/**
 * Exception utilizada para lançar erros de negocio
 * 
 * @author Fernando Separovic
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public BusinessException(final String erro) {
		super(erro);
	}
}
