package com.magalu.logistica.app.agendamento.api.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model que representa uma solicitação de agendamento de envio de comunicação
 * 
 * @author Fernando Separovic
 *
 */
@Valid
public class SolicitacaoAgendamento {

	@NotEmpty
	@Valid
	private Set<Destinatario> destinatarios;
	
	@NotBlank
	@Size(min = 0, max = 300)
	private String mensagem;
	
	@NotNull
	private Date dataHoraParaEnvio;


	/**
	 * @return the destinatarios
	 */
	public Set<Destinatario> getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios the destinatarios to set
	 */
	public void setDestinatarios(final Set<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
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


	@Override
	public int hashCode() {
		return Objects.hash(dataHoraParaEnvio, destinatarios, mensagem);
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
		SolicitacaoAgendamento other = (SolicitacaoAgendamento) obj;
		return Objects.equals(dataHoraParaEnvio, other.dataHoraParaEnvio)
				&& Objects.equals(destinatarios, other.destinatarios) && Objects.equals(mensagem, other.mensagem);
	}

	@Override
	public String toString() {
		return "SolicitacaoAgendamento [destinatarios=" + destinatarios + ", mensagem=" + mensagem
				+ ", dataHoraParaEnvio=" + dataHoraParaEnvio + "]";
	}
}
