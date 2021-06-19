package com.magalu.logistica.app.agendamento.api.model;

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
	
	private Short codigoCanal;
	private String descricaoCanal;


	private CanalComunicacaoEnum(final Integer codigoCanal, final String descricaoCanal) {
		this.codigoCanal = codigoCanal.shortValue();
		this.descricaoCanal = descricaoCanal;
	}


	/**
	 * @return the codigoCanal
	 */
	public Short getCodigoCanal() {
		return codigoCanal;
	}


	/**
	 * @return the descricaoCanal
	 */
	public String getDescricaoCanal() {
		return descricaoCanal;
	}
}
