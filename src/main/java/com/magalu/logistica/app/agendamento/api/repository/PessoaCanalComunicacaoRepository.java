package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacaoId;

@Repository
public interface PessoaCanalComunicacaoRepository 
	extends PagingAndSortingRepository<PessoaCanalComunicacao, PessoaCanalComunicacaoId> {

}
