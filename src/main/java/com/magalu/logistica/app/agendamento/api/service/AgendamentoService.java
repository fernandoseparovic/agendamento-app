package com.magalu.logistica.app.agendamento.api.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacaoId;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacaoId;
import com.magalu.logistica.app.agendamento.api.domain.StatusComunicacao;
import com.magalu.logistica.app.agendamento.api.enuns.StatusComunicacaoEnum;
import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.model.SolicitacaoAgendamento;
import com.magalu.logistica.app.agendamento.api.model.Destinatario;
import com.magalu.logistica.app.agendamento.api.repository.AgendamentoRepository;
import com.magalu.logistica.app.agendamento.api.repository.DestinatarioComunicacaoRepository;
import com.magalu.logistica.app.agendamento.api.repository.PessoaCanalComunicacaoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private PessoaCanalComunicacaoRepository pessoaCanalComunicacaoRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private DestinatarioComunicacaoRepository destinatarioComunicacaoRepository;


	/**
	 * Agenda um envio de comunicação
	 * 
	 * @param solicitacaoAgendamento Agendamento de comunicação com seus respectivos destinatarios e canais
	 * @return agendamento persistido no banco
	 * @throws BusinessException Caso os ids de pessoa canal informados não forem encontrados no banco
	 */
	public SolicitacaoAgendamento agendarEnvio(final SolicitacaoAgendamento solicitacaoAgendamento) 
			throws BusinessException {
		
		// Verifica se existem as pessoas com seus respectivos canais de comunicação para envio
		verificarExistenciaPessoaCanalComunicacao(solicitacaoAgendamento.getDestinatarios());
		
		// Persiste no banco o agendamento e seus respectivos destinatarios comunicação
		persistirAgendamentoDestinatarios(solicitacaoAgendamento);

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
		
		// Verifica se o agendamento informado existe
		verificarExistenciaAgendamento(idAgendamento);

		// Deleta no banco o agendametno e seus respectivos destinatarios comunicações
		deletarAgendamentoDestinatarios(idAgendamento);
	}


	/**
	 * Persiste nas tabelas logistica.agendamento e logistica.destinatario_comunicacao
	 * 
	 * @param solicitacaoAgendamento Agendamento de comunicação com seus respectivos destinatarios e canais
	 */
	@Transactional
	private void persistirAgendamentoDestinatarios(final SolicitacaoAgendamento solicitacaoAgendamento) {
		
		// Transforma o agendamento do request no objeto para persistir no banco
		final com.magalu.logistica.app.agendamento.api.domain.Agendamento agendamentoDomain = 
				new com.magalu.logistica.app.agendamento.api.domain.Agendamento();
		agendamentoDomain.setMensagem(solicitacaoAgendamento.getMensagem());
		agendamentoDomain.setDataHoraCriacao(new Date());
		agendamentoDomain.setDataHoraParaEnvio(solicitacaoAgendamento.getDataHoraParaEnvio());		

		// Persiste na tabela logistica.agendamento 
		final com.magalu.logistica.app.agendamento.api.domain.Agendamento agendamentoDomainPersistido = 
				agendamentoRepository.save(agendamentoDomain);


		// Transforma o Destinatario comunicação do resquest no objeto para persistir no banco
		final StatusComunicacao statusComunicacao = new StatusComunicacao();
		statusComunicacao.setIdStatusComunicacao(StatusComunicacaoEnum.AGUARDANDO_ENVIO.getCodigoStatus());
		
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
	 * Busca os idsPessoasCanais no banco caso não encontre lança exception
	 * 
	 * @param destinatarios lista de destinatarios e seus respectivos canais de comunicação
	 * @return ids pessoas canal comunicação
	 * @throws BusinessException Caso os ids de pessoa canal informados não forem encontrados no banco
	 */
	private void verificarExistenciaPessoaCanalComunicacao(final Set<Destinatario> destinatarios) 
			throws BusinessException {
		
		// Busca as pessoas canal comunicação no banco
		final Set<PessoaCanalComunicacaoId> idsPessoaCanalRequest = 
			destinatarios.stream().map(dest -> {
				final PessoaCanalComunicacaoId pessoaCanalComunicacaoId = new PessoaCanalComunicacaoId();
				pessoaCanalComunicacaoId.setIdPessoa(dest.getIdPessoaDestinatario());
				pessoaCanalComunicacaoId.setIdCanalComunicacao(dest.getCanalComunicacao().getCodigoCanal());
				return pessoaCanalComunicacaoId;
			}).collect(Collectors.toSet());
		
		final Iterable<PessoaCanalComunicacao> itPessoaCanal = 
				pessoaCanalComunicacaoRepository.findAllById(idsPessoaCanalRequest);
		
		// Extrai id que seria o codigo da pessoa e do canal de comunicação 
		final Set<PessoaCanalComunicacaoId> idsPessoasCanalBanco = 
				StreamSupport.stream(itPessoaCanal.spliterator(), false)
					 		 .map(PessoaCanalComunicacao::getId)
					 		 .collect(Collectors.toSet());
		
		// Compara os ids do request com os ids do banco para verificar se o que veio no request existe no banco
		final Set<PessoaCanalComunicacaoId> idsPessoaCanalInexistentesNoBanco = 
				idsPessoaCanalRequest.stream()
							 		 .filter(request -> !idsPessoasCanalBanco.contains(request))
							 		 .collect(Collectors.toSet());
		
		// Caso existam ids que não estão no banco é lançada uma exceção
		if(!idsPessoaCanalInexistentesNoBanco.isEmpty()) {
			throw new BusinessException("Destinatarios Canal inexistentes no banco" + idsPessoaCanalInexistentesNoBanco);
		}
	}

	/**
	 * Verifica se existe um agendamento com o id informado, caso não exista lança execeção
	 * 
	 * @param idAgendamento id do agendamento
	 * @throws BusinessException Caso o agendamento não exista
	 */
	private void verificarExistenciaAgendamento(final Integer idAgendamento) throws BusinessException {
		final Optional<com.magalu.logistica.app.agendamento.api.domain.Agendamento> optionalAgendamentoDomain = 
				agendamentoRepository.findById(idAgendamento);

		if (!optionalAgendamentoDomain.isPresent()) {
			throw new BusinessException("Agendamento inexistente id: " + idAgendamento);
		}
	}

	/**
	 * Deleta os registros das tabelas logistica.agendamento e logistica.destinatario_comunicacao 
	 * pelo id do agendamento
	 * 
	 * @param idAgendamento id do agendamento
	 */
	@Transactional
	private void deletarAgendamentoDestinatarios(final Integer idAgendamento) {
		destinatarioComunicacaoRepository.deleteByIdAgendamento(idAgendamento);

		agendamentoRepository.deleteById(idAgendamento);
	}
}
