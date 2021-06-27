package com.magalu.logistica.app.agendamento.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	private static final String FORMATO_DATA_HORA_BRASIL = "dd/MM/yyyy HH:mm:ss";


	@Test
	public void toDateFinalDiaTest() throws ParseException {
		// Teste nulo
		Assert.assertNull(DateUtils.toDateFinalDia(null));
		
		// Teste de conversão
		final LocalDate dataAConverter = LocalDate.of(2020, 1, 28);
		
		final Date resultadoEsperado = new SimpleDateFormat(FORMATO_DATA_HORA_BRASIL).parse("28/01/2020 23:59:59");
		Assert.assertEquals(DateUtils.toDateFinalDia(dataAConverter), resultadoEsperado);
	}
	
	@Test
	public void toDateInicioDiaTest() throws ParseException {
		// Teste nulo
		Assert.assertNull(DateUtils.toDateInicioDia(null));
		
		// Teste de conversão
		final LocalDate dataAConverter = LocalDate.of(2020, 1, 29);
		final Date resultadoEsperado = new SimpleDateFormat(FORMATO_DATA_HORA_BRASIL).parse("29/01/2020 00:00:00");
		Assert.assertEquals(DateUtils.toDateInicioDia(dataAConverter), resultadoEsperado);
	}
	
	@Test
	public void toDate() throws ParseException {
		// Teste nulo
		Assert.assertNull(DateUtils.toDate(null, null, null));
		
		// Teste de conversão
		final String formatoData = "yyyy-MM-dd HH:mm:ss";
		final LocalDate dataAConverter = LocalDate.of(2020, 1, 30);
		final Date resultadoEsperado = new SimpleDateFormat(formatoData).parse("2020-01-30 14:00:00");
		Assert.assertEquals(DateUtils.toDate(dataAConverter, "14:00:00", formatoData), resultadoEsperado);
	}
}
