package com.magalu.logistica.app.agendamento.api.enuns;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum com todos os canais possiveis de comunicação
 * 
 * @author Fernando Separovic
 *
 */
public enum CanalComunicacaoEnum {
	
	/**
	 * 1 - Email
	 */
	EMAIL(1, "Email"),
	
	/**
	 * 2 - Sms
	 */
	SMS(2, "Sms"),
	
	/**
	 * 3 - Push
	 */
	PUSH(3, "Push"),
	
	/**
	 * 4 - Whatsapp
	 */
	WHATSAPP(4, "Whatsapp"),
	
	;
	
	private Integer codigoCanal;
	private String descricaoCanal;
	
	private static Map<Integer, CanalComunicacaoEnum> mapCanalComunicacaoEnum = 
			Stream.of(values())
				  .collect(Collectors.toMap(CanalComunicacaoEnum::getCodigoCanal, Function.identity()));


	/**
	 * Construtor enum
	 * 
	 * @param codigoCanal
	 * @param descricaoCanal
	 */
	private CanalComunicacaoEnum(final Integer codigoCanal, final String descricaoCanal) {
		this.codigoCanal = codigoCanal;
		this.descricaoCanal = descricaoCanal;
	}
	
	/**
	 * Busca um enum a partir do codigo do canal
	 * 
	 * @param codigoCanal
	 * @return {@link CanalComunicacaoEnum}
	 */
	public static CanalComunicacaoEnum getCanalComunicacaoEnum(final Integer codigoCanal) {
		return mapCanalComunicacaoEnum.get(codigoCanal);
	}


	/**
	 * @return the codigoCanal
	 */
	public Integer getCodigoCanal() {
		return codigoCanal;
	}


	/**
	 * @return the descricaoCanal
	 */
	public String getDescricaoCanal() {
		return descricaoCanal;
	}
}
