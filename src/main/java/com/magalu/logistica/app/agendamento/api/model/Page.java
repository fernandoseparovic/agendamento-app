package com.magalu.logistica.app.agendamento.api.model;

import java.util.Objects;

/**
 * Contem os atributos de uma paginação
 * 
 * @author Fernando Separovic
 *
 */
public class Page {

	private Integer pageNumber;
	private Integer perPage;
	private Integer totalPages;
	private Long totalElements;


	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(final Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public Page pageNumber(final Integer pageNumber) {
		this.pageNumber = pageNumber;
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

	@Override
	public int hashCode() {
		return Objects.hash(pageNumber, perPage, totalElements, totalPages);
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
		Page other = (Page) obj;
		return Objects.equals(pageNumber, other.pageNumber) && Objects.equals(perPage, other.perPage)
				&& Objects.equals(totalElements, other.totalElements) && Objects.equals(totalPages, other.totalPages);
	}

	@Override
	public String toString() {
		return "Page [pageNumber=" + pageNumber + ", perPage=" + perPage + ", totalPages=" + totalPages
				+ ", totalElements=" + totalElements + "]";
	}
}
