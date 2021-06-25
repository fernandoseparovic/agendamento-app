package com.magalu.logistica.app.agendamento.api.model;

import java.util.Objects;
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
	 * @param page the page to set
	 */
	public AgendamentoPaginado page(final Page page) {
		this.page = page;
		return this;
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
	
	/**
	 * @param agendamentos the agendamentos to set
	 */
	public AgendamentoPaginado agendamentos(final Set<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agendamentos, page);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AgendamentoPaginado other = (AgendamentoPaginado) obj;
		return Objects.equals(agendamentos, other.agendamentos) && Objects.equals(page, other.page);
	}

	@Override
	public String toString() {
		return "AgendamentoPaginado [page=" + page + ", agendamentos=" + agendamentos + "]";
	}
}
