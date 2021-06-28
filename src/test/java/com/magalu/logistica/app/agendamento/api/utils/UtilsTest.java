package com.magalu.logistica.app.agendamento.api.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.data.domain.PageRequest;

public class UtilsTest {

	@Test
	public void montarPageRequestTest() {
		final PageRequest pageRequestNull = Utils.montarPageRequest(null, null);
		assertEquals(0, pageRequestNull.getPageNumber());
		assertEquals(100, pageRequestNull.getPageSize());
		
		final PageRequest page1PerPage10 = Utils.montarPageRequest(2, 10);
		assertEquals(1, page1PerPage10.getPageNumber());
		assertEquals(10, page1PerPage10.getPageSize());
	}
}
