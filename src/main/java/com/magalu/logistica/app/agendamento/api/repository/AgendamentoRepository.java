package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.magalu.logistica.app.agendamento.api.domain.Agendamento;


public interface AgendamentoRepository extends PagingAndSortingRepository<Agendamento, Integer> {

}
