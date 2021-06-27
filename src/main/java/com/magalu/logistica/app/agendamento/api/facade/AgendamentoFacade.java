package com.magalu.logistica.app.agendamento.api.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.model.AgendamentoPaginado;
import com.magalu.logistica.app.agendamento.api.model.ConsultaAgendamento;
import com.magalu.logistica.app.agendamento.api.model.SolicitacaoAgendamento;
import com.magalu.logistica.app.agendamento.api.service.AgendamentoService;
import com.magalu.logistica.app.agendamento.api.service.PessoaService;

/**
 * Facade responsavel por simplificar a exposição das principais funcionalidades relativas 
 * a manutenção de agendamentos de comunicação
 * 
 * @author Fernando Separovic
 *
 */
@Service
public class AgendamentoFacade {

	@Autowired
	private AgendamentoService agendamentoService;
	
	@Autowired
	private PessoaService pessoaService;
	

	private static final Logger LOGGER = LoggerFactory.getLogger(AgendamentoFacade.class);

	/**
	 * Agenda um envio de comunicação
	 * 
	 * @param solicitacaoAgendamento Agendamento de comunicação com seus respectivos destinatarios e canais
	 * @return agendamento persistido no banco
	 * @throws BusinessException Caso os ids de pessoa canal informados não forem encontrados no banco
	 */
	public SolicitacaoAgendamento agendarEnvio(final SolicitacaoAgendamento solicitacaoAgendamento) 
			throws BusinessException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("agendarEnvio entrada: {}", solicitacaoAgendamento);
		}

		// Verifica se existem as pessoas com seus respectivos canais de comunicação para envio
		pessoaService.verificarExistenciaPessoaCanalComunicacao(solicitacaoAgendamento.getDestinatarios());
		
		// Persiste no banco o agendamento e seus respectivos destinatarios comunicação
		agendamentoService.persistirAgendamentoDestinatarios(solicitacaoAgendamento);

		return solicitacaoAgendamento;
	}


	/**
	 * Verifica se existe o agendamento com o id informado na entrada e caso positivo 
	 * Deleta os registros das tabelas logistica.agendamento e logistica.destinatario_comunicacao  
	 * 
	 * @param idAgendamento id do Agendamento
	 * @throws BusinessException Caso não exista agendamento com o id informado
	 */
	public void deletarAgendamento(final Integer idAgendamento) throws BusinessException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deletarAgendamento entrada: {}", idAgendamento);
		}

		// Verifica se o agendamento informado existe
		agendamentoService.verificarExistenciaAgendamento(idAgendamento);

		// Deleta no banco o agendametno e seus respectivos destinatarios comunicações
		agendamentoService.deletarAgendamentoDestinatarios(idAgendamento);
	}
	
	
	/**
	 * Busca paginada de agendamentos conforme parametros passados na entrada
	 * 
	 * @param consultaAgendamento filtro para consulta paginada de agendamentos
	 * @return {@link AgendamentoPaginado}
	 */
	public AgendamentoPaginado buscarAgendamento(final ConsultaAgendamento consultaAgendamento) {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("buscarAgendamento entrada: {}", consultaAgendamento);
		}

		return agendamentoService.buscarAgendamento(consultaAgendamento);
	}
}
