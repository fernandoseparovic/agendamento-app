package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacaoId;

/**
 * Repositorio de acesso a base de dados para a entidade logistica.pessoa_canal_comunicacao
 * 
 * @author Fernando Separovic
 *
 */
@Repository
public interface PessoaCanalComunicacaoRepository 
	extends PagingAndSortingRepository<PessoaCanalComunicacao, PessoaCanalComunicacaoId> {

}
