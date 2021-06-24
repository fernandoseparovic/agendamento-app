package com.magalu.logistica.app.agendamento.api.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.magalu.logistica.app.agendamento.api.enuns.StatusComunicacaoEnum;

/**
 * Model que representa um agendamento de envio de comunicação
 * 
 * @author Fernando Separovic
 *
 */
@Valid
public class Agendamento {

	@NotEmpty
	@Valid
	private Integer idAgendamento;

	@NotBlank
	@Size(min = 0, max = 300)
	private String mensagem;

	@NotNull
	private Date dataHoraCriacao;

	@NotNull
	private Date dataHoraParaEnvio;
	
	@NotNull
	private StatusComunicacaoEnum statusComunicacao;


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
	 * @param idAgendamento the idAgendamento to set
	 */
	public Agendamento idAgendamento(final Integer idAgendamento) {
		this.idAgendamento = idAgendamento;
		return this;
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
	 * @param mensagem the mensagem to set
	 */
	public Agendamento mensagem(final String mensagem) {
		this.mensagem = mensagem;
		return this;
	}

	/**
	 * @return the dataHoraCriacao
	 */
	public Date getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	/**
	 * @param dataHoraCriacao the dataHoraCriacao to set
	 */
	public void setDataHoraCriacao(final Date dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}
	
	/**
	 * @param dataHoraCriacao the dataHoraCriacao to set
	 */
	public Agendamento dataHoraCriacao(final Date dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
		return this;
	}

	/**
	 * @return the dataHoraParaEnvio
	 */
	public Date getDataHoraParaEnvio() {
		return dataHoraParaEnvio;
	}

	/**
	 * @param dataHoraParaEnvio the dataHoraParaEnvio to set
	 */
	public void setDataHoraParaEnvio(final Date dataHoraParaEnvio) {
		this.dataHoraParaEnvio = dataHoraParaEnvio;
	}
	
	/**
	 * @param dataHoraParaEnvio the dataHoraParaEnvio to set
	 */
	public Agendamento dataHoraParaEnvio(final Date dataHoraParaEnvio) {
		this.dataHoraParaEnvio = dataHoraParaEnvio;
		return this;
	}

	/**
	 * @return the statusComunicacaoEnum
	 */
	public StatusComunicacaoEnum getStatusComunicacao() {
		return statusComunicacao;
	}

	/**
	 * @param statusComunicacaoEnum the statusComunicacaoEnum to set
	 */
	public void setStatusComunicacaoEnum(final StatusComunicacaoEnum statusComunicacao) {
		this.statusComunicacao = statusComunicacao;
	}
	
	/**
	 * @param statusComunicacaoEnum the statusComunicacaoEnum to set
	 */
	public Agendamento statusComunicacaoEnum(final StatusComunicacaoEnum statusComunicacao) {
		this.statusComunicacao = statusComunicacao;
		return this;
	}
	
}
