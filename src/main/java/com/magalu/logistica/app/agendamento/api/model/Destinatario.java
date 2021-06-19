package com.magalu.logistica.app.agendamento.api.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Representa um destinatario com um canal de comunicação para o envio da mensagem
 * 
 * @author Fernando Separovic
 *
 */
@Valid
public class Destinatario {

	@NotNull
	private Integer idPessoaDestinatario;
	
	@NotNull
	private CanalComunicacaoEnum canalComunicacao;

	/**
	 * @return the idPessoaDestinatario
	 */
	public Integer getIdPessoaDestinatario() {
		return idPessoaDestinatario;
	}

	/**
	 * @param idPessoaDestinatario the idPessoaDestinatario to set
	 */
	public void setIdPessoaDestinatario(final Integer idPessoaDestinatario) {
		this.idPessoaDestinatario = idPessoaDestinatario;
	}

	/**
	 * @return the canalComunicacao
	 */
	public CanalComunicacaoEnum getCanalComunicacao() {
		return canalComunicacao;
	}

	/**
	 * @param canalComunicacao the canalComunicacao to set
	 */
	public void setCanalComunicacao(final CanalComunicacaoEnum canalComunicacao) {
		this.canalComunicacao = canalComunicacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(canalComunicacao, idPessoaDestinatario);
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
		Destinatario other = (Destinatario) obj;
		return canalComunicacao == other.canalComunicacao
				&& Objects.equals(idPessoaDestinatario, other.idPessoaDestinatario);
	}

	@Override
	public String toString() {
		return "Destinatario [idPessoaDestinatario=" + idPessoaDestinatario + ", canalComunicacao=" + canalComunicacao
				+ "]";
	}
	
	

}
