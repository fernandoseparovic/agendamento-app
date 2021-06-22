package com.magalu.logistica.app.agendamento.api.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.DestinatarioComunicacaoId;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacaoId;
import com.magalu.logistica.app.agendamento.api.domain.StatusComunicacao;
import com.magalu.logistica.app.agendamento.api.enuns.StatusComunicacaoEnum;
import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.model.Agendamento;
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
	 * @param agendamento Agendamento de comunicação com seus respectivos destinatarios e canais
	 * @return agendamento persistido no banco
	 * @throws BusinessException Caso os ids de pessoa canal informados não forem encontrados no banco
	 */
	public Agendamento agendarEnvio(final Agendamento agendamento) throws BusinessException {
		
		// Transforma o agendamento do request no objeto para persistir no banco
		final com.magalu.logistica.app.agendamento.api.domain.Agendamento agendamentoDomain = 
				new com.magalu.logistica.app.agendamento.api.domain.Agendamento();
		agendamentoDomain.setMensagem(agendamento.getMensagem());
		agendamentoDomain.setDataHoraCriacao(new Date());
		agendamentoDomain.setDataHoraParaEnvio(agendamento.getDataHoraParaEnvio());		

		// TODO verificar porque não esta funcionando o save cascade
		final com.magalu.logistica.app.agendamento.api.domain.Agendamento agendamentoDomainPersistido = 
				agendamentoRepository.save(agendamentoDomain);


		// Ids das pessoas com seus respectivos canais de comunicação para envio
		final Set<PessoaCanalComunicacaoId> idsPessoaCanal = 
				buscarPessoaCanalComunicacao(agendamento.getDestinatarios());

		// Transforma o Destinatario comunicação do resquest no objeto para persistir no banco
		final Set<DestinatarioComunicacao> setDestinatarioComunicacao = new HashSet<>();
		idsPessoaCanal.forEach(pessoaCanal -> {
			final StatusComunicacao statusComunicacao = new StatusComunicacao();
			statusComunicacao.setIdStatusComunicacao(StatusComunicacaoEnum.AGUARDANDO_ENVIO.getCodigoStatus());

			final DestinatarioComunicacao destinatarioComunicacao = new DestinatarioComunicacao();
			destinatarioComunicacao.setId(new DestinatarioComunicacaoId());
			destinatarioComunicacao.getId().setIdAgendamento(agendamentoDomainPersistido.getIdAgendamento());
			destinatarioComunicacao.getId().setIdCanalComunicacao(pessoaCanal.getIdCanalComunicacao());
			destinatarioComunicacao.getId().setIdPessoaDestinatario(pessoaCanal.getIdPessoa());
			destinatarioComunicacao.setStatusComunicacao(statusComunicacao);
			
			setDestinatarioComunicacao.add(destinatarioComunicacao);
		});
		
		destinatarioComunicacaoRepository.saveAll(setDestinatarioComunicacao);

		return agendamento;
	}

	/**
	 * Busca os idsPessoasCanais no banco caso não encontre lança exception
	 * 
	 * @param destinatarios lista de destinatarios e seus respectivos canais de comunicação
	 * @return ids pessoas canal comunicação
	 * @throws BusinessException Caso os ids de pessoa canal informados não forem encontrados no banco
	 */
	private Set<PessoaCanalComunicacaoId> buscarPessoaCanalComunicacao(final Set<Destinatario> destinatarios) 
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

		return idsPessoasCanalBanco;
	}	
}
