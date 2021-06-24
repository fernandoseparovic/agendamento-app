package com.magalu.logistica.app.agendamento.api.model;

/**
 * Contem os atributos de uma paginação
 * 
 * @author Fernando Separovic
 *
 */
public class Page {

	private Integer page;
	private Integer perPage;
	private Integer totalPages;
	private Long totalElements;

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
	 * @param page the page to set
	 */
	public Page page(final Integer page) {
		this.page = page;
		return this;
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
	
	/**
	 * @param perPage the perPage to set
	 */
	public Page perPage(final Integer perPage) {
		this.perPage = perPage;
		return this;
	}

	/**
	 * @return the totalPages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(final Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	/**
	 * @param totalPages the totalPages to set
	 */
	public Page totalPages(final Integer totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	/**
	 * @return the totalElements
	 */
	public Long getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(final Long totalElements) {
		this.totalElements = totalElements;
	}
	
	/**
	 * @param totalElements the totalElements to set
	 */
	public Page totalElements(final Long totalElements) {
		this.totalElements = totalElements;
		return this;
	}
}
