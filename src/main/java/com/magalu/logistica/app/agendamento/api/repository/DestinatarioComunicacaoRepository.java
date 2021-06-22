package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacaoId;

@Repository
public interface DestinatarioComunicacaoRepository 
	extends PagingAndSortingRepository<DestinatarioComunicacao, DestinatarioComunicacaoId> {

}
