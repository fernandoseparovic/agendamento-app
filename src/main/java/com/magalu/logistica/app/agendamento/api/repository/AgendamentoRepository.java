package com.magalu.logistica.app.agendamento.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.magalu.logistica.app.agendamento.api.domain.Agendamento;

@Repository
public interface AgendamentoRepository extends PagingAndSortingRepository<Agendamento, Integer> {

}
