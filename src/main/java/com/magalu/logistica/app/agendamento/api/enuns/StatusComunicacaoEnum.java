package com.magalu.logistica.app.agendamento.api.enuns;

public enum StatusComunicacaoEnum {

	/**
	 * 1 - Aguardando envio
	 */
	AGUARDANDO_ENVIO(1),

	/**
	 * 2 - Enviado
	 */
	ENVIADO(2),

	/**
	 * 3 - Erro ao enviar
	 */
	ERRO_AO_ENVIAR(3),

	;

	private Short codigoStatus;

	private StatusComunicacaoEnum(final Integer codigoStatus) {
		this.codigoStatus = codigoStatus.shortValue();
	}

	/**
	 * @return the codigoStatus
	 */
	public Short getCodigoStatus() {
		return codigoStatus;
	}
}
