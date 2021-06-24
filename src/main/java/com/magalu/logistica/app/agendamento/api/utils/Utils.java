package com.magalu.logistica.app.agendamento.api.utils;

import org.springframework.data.domain.PageRequest;

/**
 * Classe com metodos utilitarios para todo o projeto
 * 
 * @author Fernando Separovic
 *
 */
public class Utils {

	/**
	 * Construtor privado para evitar que a classe seja instanciada
	 */
	private Utils() {
		super();
	}

	/**
	 * Monta um objeto {@link PageRequest} utilizado em consultas paginadas
	 * 
	 * @param page    pagina
	 * @param perPage itens por pagina
	 * @return {@link PageRequest}
	 */
	public static PageRequest montarPageRequest(final Integer page, final Integer perPage) {
		return PageRequest.of(page == null ? 0 : page - 1, 
							  perPage == null ? 100 : perPage);
	}
}
