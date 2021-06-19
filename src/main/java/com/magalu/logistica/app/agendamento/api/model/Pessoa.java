package com.magalu.logistica.app.agendamento.api.model;

/**
 * Representa uma pessoa envolvida na comunicação
 * 
 * @author Fernando Separovic
 *
 */
public class Pessoa {

	private Integer idPessoa;
	private String nome;
	private String email;
	private String celular;
	private String push;


	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(final Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(final String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(final String celular) {
		this.celular = celular;
	}

	/**
	 * @return the push
	 */
	public String getPush() {
		return push;
	}

	/**
	 * @param push the push to set
	 */
	public void setPush(String push) {
		this.push = push;
	}

}
