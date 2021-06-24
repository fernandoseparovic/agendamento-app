package com.magalu.logistica.app.agendamento.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * Classe com metodos utilitarios de datas para todo o projeto
 * 
 * @author Fernando Separovic
 *
 */
public class DateUtils {

	private static final String FORMATO_DATA_HORA = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Construtor privado para evitar que a classe seja instanciada
	 */
	private DateUtils() {
		super();
	}
	
	
	public static java.util.Date toDateFinalDia(final java.time.LocalDate localDate) {
		return toDate(localDate, "23:59:59", FORMATO_DATA_HORA);
	}
	
	public static java.util.Date toDateInicioDia(final java.time.LocalDate localDate) {
		return toDate(localDate, "00:00:00", FORMATO_DATA_HORA);
	}
	

	public static java.util.Date toDate(final java.time.LocalDate localDate, final String hora, final String formato) {
		if (localDate != null && StringUtils.isNotBlank(hora) && StringUtils.isNotBlank(formato)) {
			try {
				return new SimpleDateFormat(formato).parse(localDate.toString() + " " + hora);
			} catch (final ParseException e) {
				return null;
			}
		}
		return null;
	}
}
