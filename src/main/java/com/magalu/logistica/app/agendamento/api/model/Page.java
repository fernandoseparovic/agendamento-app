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
	private Integer totalElements;

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
	 * @return the totalElements
	 */
	public Integer getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(final Integer totalElements) {
		this.totalElements = totalElements;
	}
}
