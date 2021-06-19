package com.magalu.logistica.app.agendamento.api.model;

import java.util.Set;

/**
 * Paginação para agendamentos
 * 
 * @author Fernando Separovic
 *
 */
public class AgendamentoPaginado {

	private Page page;
	private Set<Agendamento> agendamentos;


	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(final Page page) {
		this.page = page;
	}

	/**
	 * @return the agendamentos
	 */
	public Set<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	/**
	 * @param agendamentos the agendamentos to set
	 */
	public void setAgendamentos(final Set<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

}
