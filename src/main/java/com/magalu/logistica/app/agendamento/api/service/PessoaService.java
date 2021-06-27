package com.magalu.logistica.app.agendamento.api.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacao;
import com.magalu.logistica.app.agendamento.api.domain.PessoaCanalComunicacaoId;
import com.magalu.logistica.app.agendamento.api.enuns.CanalComunicacaoEnum;
import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.model.Destinatario;
import com.magalu.logistica.app.agendamento.api.repository.PessoaCanalComunicacaoRepository;

/**
 * Service para tratar atividades relacionadas com pessoa.
 * 
 * @author Fernando Separovic
 *
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaCanalComunicacaoRepository pessoaCanalComunicacaoRepository;


	/**
	 * Busca os idsPessoasCanais no banco caso não encontre lança exception
	 * 
	 * @param destinatarios lista de destinatarios e seus respectivos canais de comunicação
	 * @return ids pessoas canal comunicação
	 * @throws BusinessException Caso os ids de pessoa canal informados não forem encontrados no banco
	 */
	public void verificarExistenciaPessoaCanalComunicacao(final Set<Destinatario> destinatarios) 
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
		final Set<Destinatario> destinatariosCanalInexistentesNoBanco = 
				idsPessoaCanalRequest.stream()
							 		 .filter(request -> !idsPessoasCanalBanco.contains(request))
							 		 .map(request -> new Destinatario()
				 				 				.idPessoaDestinatario(request.getIdPessoa())
				 				 				.canalComunicacao(CanalComunicacaoEnum.getCanalComunicacaoEnum(
				 				 						request.getIdCanalComunicacao())))
							 		 .collect(Collectors.toSet());
		
		// Caso existam ids que não estão no banco é lançada uma exceção
		if(!destinatariosCanalInexistentesNoBanco.isEmpty()) {
			throw new BusinessException("Destinatarios Canal inexistentes no banco" + destinatariosCanalInexistentesNoBanco);
		}
	}
}
