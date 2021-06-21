package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacaoId;


public interface PessoaCanalComunicacaoRepository 
	extends PagingAndSortingRepository<PessoaCanalComunicacao, PessoaCanalComunicacaoId> {

}
