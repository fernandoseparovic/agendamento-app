package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacaoId;

/**
 * Repositorio de acesso a base de dados para a entidade logistica.destinatario_comunicacao
 * 
 * @author Fernando Separovic
 *
 */
@Repository
public interface DestinatarioComunicacaoRepository 
	extends PagingAndSortingRepository<DestinatarioComunicacao, DestinatarioComunicacaoId> {

	@Modifying
	@Query("delete from DestinatarioComunicacao d where d.id.idAgendamento=:idAgendamento")
	void deleteByIdAgendamento(final Integer idAgendamento);
}
