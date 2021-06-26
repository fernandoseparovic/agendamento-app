package com.magalu.logistica.app.agendamento.api.controller;


import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.facade.AgendamentoFacade;
import com.magalu.logistica.app.agendamento.api.model.AgendamentoPaginado;
import com.magalu.logistica.app.agendamento.api.model.ConsultaAgendamento;
import com.magalu.logistica.app.agendamento.api.model.SolicitacaoAgendamento;

import io.micrometer.core.lang.Nullable;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RestController
@OpenAPIDefinition(info = @Info(title = "Agendamento API", 
								description = "Permite realizar agendamentos de comunicação, "
										+ "listar e deletar os agendamentos já existentes, ",
								version = "v1", 
								license = @License(name = "GPL-3.0 License", 
												   url = "https://www.gnu.org/licenses/gpl-3.0.pt-br.html")))
@RequestMapping("/v1/agendamento")
public class AgendamentoController {

	@Autowired
    private AgendamentoFacade agendamentoFacade;


	@Operation(summary = "Agenda um envio de comunicação")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Agendamento realizado com sucesso", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 schema = @Schema(implementation = SolicitacaoAgendamento.class)) }),
			@ApiResponse(responseCode = "400", description = "Parâmetros invalidos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno", content = @Content) })
	@PutMapping
	public ResponseEntity<SolicitacaoAgendamento> agendarEnvio(
			@Valid @RequestBody final SolicitacaoAgendamento solicitacaoAgendamento) throws BusinessException {

		return new ResponseEntity<>(agendamentoFacade.agendarEnvio(solicitacaoAgendamento), HttpStatus.OK);
	}


	@Operation(summary = "Consulta agendamentos de comunicações realizadas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
							 schema = @Schema(implementation = AgendamentoPaginado.class)) }),
			@ApiResponse(responseCode = "400", description = "Parâmetros invalidos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno", content = @Content) })
	@GetMapping
	public ResponseEntity<AgendamentoPaginado> consultarAgendamento(
			@Parameter @Nullable final Integer idAgendamento,
			@Parameter @Nullable final String mensagem,
			@Parameter @Nullable @DateTimeFormat(pattern = "dd/MM/yyyy") final LocalDate dataCriacao,
			@Parameter @Nullable @DateTimeFormat(pattern = "dd/MM/yyyy") final LocalDate dataParaEnvio,
			@Parameter @Nullable final Integer page,
			@Parameter @Nullable final Integer perPage) {

		final ConsultaAgendamento consultaAgendamento = new ConsultaAgendamento();
		consultaAgendamento.setIdAgendamento(idAgendamento);
		consultaAgendamento.setMensagem(mensagem);
		consultaAgendamento.setDataCriacao(dataCriacao);
		consultaAgendamento.setDataParaEnvio(dataParaEnvio);
		consultaAgendamento.setPage(page);
		consultaAgendamento.setPerPage(perPage);

		return new ResponseEntity<>(agendamentoFacade.buscarAgendamento(consultaAgendamento), HttpStatus.OK);
	}


	@Operation(summary = "Deleta um agendamento de comunicação")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete realizado com sucesso", content = @Content),
			@ApiResponse(responseCode = "400", description = "Parâmetros invalidos", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro interno", content = @Content) })
	@DeleteMapping("/{idAgendamento}")
	public ResponseEntity<SolicitacaoAgendamento> deletarAgendamento(@PathVariable @NotNull final Integer idAgendamento) 
			throws BusinessException {

		agendamentoFacade.deletarAgendamento(idAgendamento);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
