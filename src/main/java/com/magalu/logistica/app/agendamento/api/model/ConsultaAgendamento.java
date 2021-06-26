package com.magalu.logistica.app.agendamento.api.model;

import java.time.LocalDate;

/**
 * Possui os dados utilizados na consulta de agendamento
 * 
 * @author Fernando Separovic
 *
 */
public class ConsultaAgendamento {

	private Integer idAgendamento;
	private String mensagem;
	private LocalDate dataCriacao;
	private LocalDate dataParaEnvio;
	private Integer page;
	private Integer perPage;


	/**
	 * @return the idAgendamento
	 */
	public Integer getIdAgendamento() {
		return idAgendamento;
	}

	/**
	 * @param idAgendamento the idAgendamento to set
	 */
	public void setIdAgendamento(final Integer idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the dataCriacao
	 */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(final LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the dataParaEnvio
	 */
	public LocalDate getDataParaEnvio() {
		return dataParaEnvio;
	}

	/**
	 * @param dataParaEnvio the dataParaEnvio to set
	 */
	public void setDataParaEnvio(final LocalDate dataParaEnvio) {
		this.dataParaEnvio = dataParaEnvio;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(final Integer page) {
		this.page = page;
	}

	/**
	 * @return the perPage
	 */
	public Integer getPerPage() {
		return perPage;
	}

	/**
	 * @param perPage the perPage to set
	 */
	public void setPerPage(final Integer perPage) {
		this.perPage = perPage;
	}

	@Override
	public String toString() {
		return "ConsultaAgendamento [idAgendamento=" + idAgendamento + ", mensagem=" + mensagem + ", dataCriacao="
				+ dataCriacao + ", dataParaEnvio=" + dataParaEnvio + ", page=" + page + ", perPage=" + perPage + "]";
	}
}
