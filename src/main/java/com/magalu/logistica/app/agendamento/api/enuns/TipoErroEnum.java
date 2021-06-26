package com.magalu.logistica.app.agendamento.api.enuns;

/**
 * Enum com os tipos de erros que podem ocorrer durante a execução da Api
 * 
 * @author Fernando Separovic
 *
 */
public enum TipoErroEnum {

	/**
	 * 1 - Negocio
	 */
	NEGOCIO(1),

	/**
	 * 2 - Infra
	 */
	INFRA(2),

	;

	private Integer codigoErro;

	/*
	 * Construtor enum
	 */
	private TipoErroEnum (final Integer codigoErro) {
		this.codigoErro = codigoErro;
	}

	/**
	 * @return the codigoErro
	 */
	public Integer getCodigoErro() {
		return codigoErro;
	}
}
