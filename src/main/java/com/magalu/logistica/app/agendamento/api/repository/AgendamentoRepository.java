package com.magalu.logistica.app.agendamento.api.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.magalu.logistica.app.agendamento.api.domain.Agendamento;

/**
 * Repositorio de acesso a base de dados para a entidade logistica.agendamento
 * 
 * @author Fernando Separovic
 *
 */
@Repository
public interface AgendamentoRepository extends PagingAndSortingRepository<Agendamento, Integer> {

	@Query("select agendamento from Agendamento as agendamento "
			+ "where (1 = 1) "
			+ "and (:idAgendamento is null or agendamento.idAgendamento = :idAgendamento) "
			+ "and (:mensagem is null or agendamento.mensagem like %:mensagem%) "
			+ "and ((:dataHoraCriacaoInicio is null or :dataHoraCriacaoFim is null) "
				+ "or (agendamento.dataHoraCriacao between :dataHoraCriacaoInicio and :dataHoraCriacaoFim)) "
			+ "and ((:dataHoraParaEnvioInicio is null or :dataHoraParaEnvioFim is null) "
				+ "or (agendamento.dataHoraParaEnvio between :dataHoraParaEnvioInicio and :dataHoraParaEnvioFim)) " )
	Page<Agendamento> buscarAgendamento(@Param("idAgendamento") final Integer idAgendamento,
										@Param("mensagem") final String mensagem,
										@Param("dataHoraCriacaoInicio") final Date dataHoraCriacaoInicio,
										@Param("dataHoraCriacaoFim") final Date dataHoraCriacaoFim,
										@Param("dataHoraParaEnvioInicio") final Date dataHoraParaEnvioInicio,
										@Param("dataHoraParaEnvioFim") final Date dataHoraParaEnvioFim,
										final Pageable pageable);
}
