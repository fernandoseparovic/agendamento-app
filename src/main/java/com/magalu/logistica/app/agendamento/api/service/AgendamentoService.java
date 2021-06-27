package com.magalu.logistica.app.agendamento.api.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacaoId;
import com.magalu.logistica.app.agendamento.api.domain.StatusComunicacao;
import com.magalu.logistica.app.agendamento.api.enuns.StatusComunicacaoEnum;
import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.model.Agendamento;
import com.magalu.logistica.app.agendamento.api.model.AgendamentoPaginado;
import com.magalu.logistica.app.agendamento.api.model.ConsultaAgendamento;
import com.magalu.logistica.app.agendamento.api.model.Page;
import com.magalu.logistica.app.agendamento.api.model.SolicitacaoAgendamento;
import com.magalu.logistica.app.agendamento.api.repository.AgendamentoRepository;
import com.magalu.logistica.app.agendamento.api.repository.DestinatarioComunicacaoRepository;
import com.magalu.logistica.app.agendamento.api.utils.DateUtils;
import com.magalu.logistica.app.agendamento.api.utils.Utils;

/**
 * Service para tratar da manutenção de agendamento de comunicações
 * 
 * @author Fernando Separovic
 *
 */
@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private DestinatarioComunicacaoRepository destinatarioComunicacaoRepository;


	/**
	 * Verifica se existe um agendamento com o id informado, caso não exista lança execeção
	 * 
	 * @param idAgendamento id do agendamento
	 * @throws BusinessException Caso o agendamento não exista
	 */
	public void verificarExistenciaAgendamento(final Integer idAgendamento) throws BusinessException {
		final Optional<com.magalu.logistica.app.agendamento.api.domain.Agendamento> optionalAgendamentoDomain = 
				agendamentoRepository.findById(idAgendamento);

		if (!optionalAgendamentoDomain.isPresent()) {
			throw new BusinessException("Agendamento inexistente id: " + idAgendamento);
		}
	}


	/**
	 * Persiste nas tabelas logistica.agendamento e logistica.destinatario_comunicacao
	 * 
	 * Obs: Não chamar este metodo dentro desta chasse pois não funcionará a transação
	 * 
	 * @param solicitacaoAgendamento Agendamento de comunicação com seus respectivos destinatarios e canais
	 */
	@Transactional
	public void persistirAgendamentoDestinatarios(final SolicitacaoAgendamento solicitacaoAgendamento) {
		
		// Inicia o status do agendamento com Aguardando envio
		final StatusComunicacao statusComunicacao = new StatusComunicacao();
		statusComunicacao.setIdStatusComunicacao(StatusComunicacaoEnum.AGUARDANDO_ENVIO.getCodigoStatus());

		// Transforma o agendamento do request no objeto para persistir no banco
		final com.magalu.logistica.app.agendamento.api.domain.Agendamento agendamentoDomain = 
				new com.magalu.logistica.app.agendamento.api.domain.Agendamento();
		agendamentoDomain.setMensagem(solicitacaoAgendamento.getMensagem());
		agendamentoDomain.setDataHoraCriacao(new Date());
		agendamentoDomain.setDataHoraParaEnvio(solicitacaoAgendamento.getDataHoraParaEnvio());
		agendamentoDomain.setStatusComunicacao(statusComunicacao);

		// Persiste na tabela logistica.agendamento 
		final com.magalu.logistica.app.agendamento.api.domain.Agendamento agendamentoDomainPersistido = 
				agendamentoRepository.save(agendamentoDomain);


		// Transforma o Destinatario comunicação do resquest no objeto para persistir no banco		
		final Set<DestinatarioComunicacao> setDestinatarioComunicacao = new HashSet<>();
		solicitacaoAgendamento.getDestinatarios().forEach(destinatario -> {
			final DestinatarioComunicacao destinatarioComunicacao = new DestinatarioComunicacao();
			destinatarioComunicacao.setId(new DestinatarioComunicacaoId());
			destinatarioComunicacao.getId().setIdAgendamento(agendamentoDomainPersistido.getIdAgendamento());
			destinatarioComunicacao.getId().setIdCanalComunicacao(destinatario.getCanalComunicacao().getCodigoCanal());
			destinatarioComunicacao.getId().setIdPessoaDestinatario(destinatario.getIdPessoaDestinatario());
			destinatarioComunicacao.setStatusComunicacao(statusComunicacao);
			
			setDestinatarioComunicacao.add(destinatarioComunicacao);
		});
		
		// Persiste na tabela logistica.destinatario_comunicacao
		destinatarioComunicacaoRepository.saveAll(setDestinatarioComunicacao);
	}


	/**
	 * Deleta os registros das tabelas logistica.agendamento e logistica.destinatario_comunicacao 
	 * pelo id do agendamento
	 * 
	 * Obs: Não chamar este metodo dentro desta chasse pois não funcionará a transação
	 * 
	 * @param idAgendamento id do agendamento
	 */
	@Transactional
	public void deletarAgendamentoDestinatarios(final Integer idAgendamento) {
		destinatarioComunicacaoRepository.deleteByIdAgendamento(idAgendamento);

		agendamentoRepository.deleteById(idAgendamento);
	}


	/**
	 * Busca paginada de agendamentos conforme parametros passados na entrada
	 * 
	 * @param consultaAgendamento filtro para consulta paginada de agendamentos
	 * @return {@link AgendamentoPaginado}
	 */
	public AgendamentoPaginado buscarAgendamento(final ConsultaAgendamento consultaAgendamento) {
		
		// Monta o objeto utilizado na query para paginação
		final PageRequest pageRequest = Utils.montarPageRequest(consultaAgendamento.getPage(), 
																consultaAgendamento.getPerPage());
		
		
		// Retorna do banco os agendamentos paginados
		final org.springframework.data.domain.Page<com.magalu.logistica.app.agendamento.api.domain.Agendamento> 
			agendamentoPaginadoDomain = 
				agendamentoRepository.buscarAgendamento(consultaAgendamento.getIdAgendamento(),
														consultaAgendamento.getMensagem(), 
														DateUtils.toDateInicioDia(consultaAgendamento.getDataCriacao()),
														DateUtils.toDateFinalDia(consultaAgendamento.getDataCriacao()),
														DateUtils.toDateInicioDia(consultaAgendamento.getDataParaEnvio()),
													    DateUtils.toDateFinalDia(consultaAgendamento.getDataParaEnvio()),
														pageRequest);
		
		// Transforma os agendamentos paginados do banco nos agendamentos paginados que é retornado pelo controller
		final AgendamentoPaginado agendamentoPaginadoModel = new AgendamentoPaginado();
		agendamentoPaginadoModel.setPage(
				new Page().pageNumber(agendamentoPaginadoDomain.getNumber() + 1)
						  .perPage(agendamentoPaginadoDomain.getSize())
						  .totalPages(agendamentoPaginadoDomain.getTotalPages())
						  .totalElements(agendamentoPaginadoDomain.getTotalElements()));
		
		agendamentoPaginadoModel.setAgendamentos(
				agendamentoPaginadoDomain.getContent()
							   .stream()
							   .map(entity -> 
							   		new Agendamento().idAgendamento(entity.getIdAgendamento())
													 .mensagem(entity.getMensagem())
													 .dataHoraCriacao(entity.getDataHoraCriacao())
													 .dataHoraParaEnvio(entity.getDataHoraParaEnvio())
							   						 .statusComunicacaoEnum(
							   								 StatusComunicacaoEnum.getStatusComunicacaoEnum(
							   										 entity.getStatusComunicacao().getIdStatusComunicacao())))
							   .collect(Collectors.toSet()));

		return agendamentoPaginadoModel;
	}
}
