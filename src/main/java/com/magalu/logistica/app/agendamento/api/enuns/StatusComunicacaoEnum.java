package com.magalu.logistica.app.agendamento.api.enuns;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum com os possiveis status para um agendamento de comunicação
 * 
 * @author Fernando Separovic
 *
 */
public enum StatusComunicacaoEnum {

	/**
	 * 1 - Aguardando envio
	 */
	AGUARDANDO_ENVIO(1, "Aguardando envio"),

	/**
	 * 2 - Enviado
	 */
	ENVIADO(2, "Enviado"),

	/**
	 * 3 - Erro ao enviar
	 */
	ERRO_AO_ENVIAR(3, "Erro ao enviar"),

	;

	private Integer codigoStatus;
	private String descricaoStatuas;

	private static Map<Integer, StatusComunicacaoEnum> mapStatusComunicacaoEnum = 
			Stream.of(values())
				  .collect(Collectors.toMap(StatusComunicacaoEnum::getCodigoStatus, Function.identity()));


	/**
	 * Construtor enum
	 * 
	 * @param codigoStatus
	 * @param descricaoStatuas
	 */
	private StatusComunicacaoEnum(final Integer codigoStatus, final String descricaoStatuas) {
		this.codigoStatus = codigoStatus;
		this.descricaoStatuas = descricaoStatuas;
	}
	
	/**
	 * Busca um enum a partir do codigo de status
	 * 
	 * @param codigoStatus codigo status
	 * @return {@link StatusComunicacaoEnum}
	 */
	public static StatusComunicacaoEnum getStatusComunicacaoEnum(final Integer codigoStatus) {
		return mapStatusComunicacaoEnum.get(codigoStatus);
	}

	/**
	 * @return the codigoStatus
	 */
	public Integer getCodigoStatus() {
		return codigoStatus;
	}

	/**
	 * @return the descricaoStatuas
	 */
	public String getDescricaoStatuas() {
		return descricaoStatuas;
	}
}
