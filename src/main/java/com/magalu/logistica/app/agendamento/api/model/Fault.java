package com.magalu.logistica.app.agendamento.api.model;

import java.util.List;
import java.util.Objects;

import com.magalu.logistica.app.agendamento.api.enuns.TipoErroEnum;

/**
 * Model que representa uma exceção da API
 * 
 * @author Fernando Separovic
 *
 */
public class Fault {

	private TipoErroEnum tipoErro;
	private List<String> erros;


	/**
	 * @return the tipoErro
	 */
	public TipoErroEnum getTipoErro() {
		return tipoErro;
	}

	/**
	 * @param tipoErro the tipoErro to set
	 */
	public void setTipoErro(final TipoErroEnum tipoErro) {
		this.tipoErro = tipoErro;
	}

	/**
	 * @return the erros
	 */
	public List<String> getErros() {
		return erros;
	}

	/**
	 * @param erros the erros to set
	 */
	public void setErros(final List<String> erros) {
		this.erros = erros;
	}


	@Override
	public int hashCode() {
		return Objects.hash(erros, tipoErro);
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
		Fault other = (Fault) obj;
		return Objects.equals(erros, other.erros) && tipoErro == other.tipoErro;
	}

	@Override
	public String toString() {
		return "Fault [tipoErro=" + tipoErro + ", erros=" + erros + "]";
	}
}
