package com.magalu.logistica.app.agendamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.magalu.logistica.app.agendamento.api.model.AgendamentoPaginado;
import com.magalu.logistica.app.agendamento.api.model.SolicitacaoAgendamento;
import com.magalu.logistica.app.config.AbstractTeste;
import com.magalu.logistica.app.utils.ArquivosUtil;

import cucumber.api.java8.En;


public class AgendamentoApiStepdefs extends AbstractTeste implements En {
	
	@Autowired
	private TestRestTemplate template;
	
	private ResponseEntity<?> responseEntity;

	private static final String PATH_BUSCAR_AGENDAMENTO = "src/test/resources/jsons/agendamento/buscar/";
	private static final String PATH_AGENDAR_ENVIO = "src/test/resources/jsons/agendamento/agendarEnvio/";
	private static final String PATH_DELETAR_ENVIO = "src/test/resources/jsons/agendamento/deletarEnvio/";


	public AgendamentoApiStepdefs() {
		
		Given("client has access to the /agendamento API", () -> {
		});


		When("client calls GET agendamento with {string}, {string}, {string}, {string}, {string}, {string}", 
			(String idAgendamento, String mensagem, String dataCriacao, String dataParaEnvio, 
			 String page, String perPage) -> {
					 
				final MultiValueMap<String, String> mapQueryParams = new LinkedMultiValueMap<>();
				
				if(StringUtils.isNotBlank(idAgendamento)) {
					mapQueryParams.add("idAgendamento", idAgendamento);
				}
				
				if(StringUtils.isNotBlank(mensagem)) {
					mapQueryParams.add("mensagem", mensagem);
				}
				
				if(StringUtils.isNotBlank(dataCriacao)) {
					mapQueryParams.add("dataCriacao", dataCriacao);
				}
				
				if(StringUtils.isNotBlank(dataParaEnvio)) {
					mapQueryParams.add("dataParaEnvio", dataParaEnvio);
				}
				
				if(StringUtils.isNotBlank(page)) {
					mapQueryParams.add("page", page);
				}
				
				if(StringUtils.isNotBlank(perPage)) {
					mapQueryParams.add("perPage", perPage);
				}
				
				final UriComponentsBuilder builder = UriComponentsBuilder
						.fromUri(new URI("/v1/agendamento"))
						.queryParams(mapQueryParams);

				responseEntity = template.getForEntity(builder.build().toUri(), AgendamentoPaginado.class);
		});


		When("client calls PUT agendamento with {string}, {int}", (String nomeArquivoInserirAgendamento, 
																   Integer statusCode) -> {
			final SolicitacaoAgendamento solicitacaoAgendamento = ArquivosUtil.carregaJson(
					PATH_AGENDAR_ENVIO + nomeArquivoInserirAgendamento, SolicitacaoAgendamento.class);

			final UriComponentsBuilder builder = UriComponentsBuilder.fromUri(new URI("/v1/agendamento"));
			final HttpEntity<SolicitacaoAgendamento> httpEntity = new HttpEntity<SolicitacaoAgendamento>(solicitacaoAgendamento);
			if (statusCode == HttpStatus.OK.value()) {
				responseEntity = template.exchange(builder.build().toUri(), HttpMethod.PUT, httpEntity, SolicitacaoAgendamento.class);
			} else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
				responseEntity = template.exchange(builder.build().toUri(), HttpMethod.PUT, httpEntity, Object.class);
			}
		});


		When("client calls DELETE agendamento with {string}", (String idAgendamento) -> {
			final UriComponentsBuilder builder = UriComponentsBuilder
					.fromUri(new URI("/v1/agendamento/"))
					.path(idAgendamento);
			
			responseEntity = template.exchange(builder.build().toUri(), HttpMethod.DELETE, null, Object.class);
		});


		Then("client of agendamento receives status code of {int}", (Integer statusCode) -> {
			if (statusCode == null || responseEntity == null) {
				fail();
			}

			final int statusCodeEntity = responseEntity.getStatusCodeValue();
			assertEquals("Status Code diferente do esperado: ", statusCodeEntity, statusCode.intValue());
		});


		Then("client of GET agendamento receives body of {string}", (String nomeArquivoResultado) -> {
			verificarRetorno(PATH_BUSCAR_AGENDAMENTO, nomeArquivoResultado, AgendamentoPaginado.class);
		});


		Then("client of PUT agendamento receives body of {string}", (String nomeArquivoResultado) -> {
			if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value()) {
				verificarRetorno(PATH_AGENDAR_ENVIO, nomeArquivoResultado, SolicitacaoAgendamento.class);
			} else if (responseEntity.getStatusCodeValue() == HttpStatus.BAD_REQUEST.value()) {
				verificarRetorno(PATH_AGENDAR_ENVIO, nomeArquivoResultado, Object.class);
			}
		});


		Then("client of DELETE agendamento receives body of {string}", (String nomeArquivoResultado) -> {
			verificarRetorno(PATH_DELETAR_ENVIO, nomeArquivoResultado, Object.class);
		});
	}


	private <T> void verificarRetorno(String pathArquivo, String nomeArquivoResultado, Class<T> classResultado) {
		if (StringUtils.isNotBlank(pathArquivo) && StringUtils.isNotBlank(nomeArquivoResultado)) {
			final T mensagemEsperada = ArquivosUtil.carregaJson(pathArquivo + nomeArquivoResultado, classResultado);
			final T mensagemResponse = classResultado.cast(responseEntity.getBody());

			assertEquals("Body diferente do esperado: ", mensagemEsperada, mensagemResponse);
		}
	}

}