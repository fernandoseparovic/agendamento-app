package com.magalu.logistica.app.handler;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.magalu.logistica.app.agendamento.api.enuns.TipoErroEnum;
import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.exception.InfraException;
import com.magalu.logistica.app.agendamento.api.model.Fault;

/**
 * Intercepta as exceptions e realiza os devidos tratamentos
 * Extender ResponseEntityExceptionHandler traz o tratamento de diversos problemas de segurança
 * 
 * @author Fernando Separovic
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Realiza o tratamento das exceções relacionadas com parametros invalidos
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
																  final HttpHeaders headers, 
																  final HttpStatus status, 
																  final WebRequest request) {
		final Fault fault = new Fault();
		fault.setTipoErro(TipoErroEnum.NEGOCIO);
		fault.setErros(new ArrayList<>());
		fault.setErros(ex.getBindingResult()
						 .getAllErrors()
						 .stream()
						 .map(error -> "campo: " + ((FieldError) error).getField() + " - " + error.getDefaultMessage())
						 .collect(Collectors.toList()));
		return new ResponseEntity<>(fault, status);
	}


	@ExceptionHandler({ BusinessException.class, 
						InfraException.class })
	public final ResponseEntity<Fault> handleBusinessException(final Exception ex, 
															   final WebRequest request) {
		TipoErroEnum tipoErroEnum = TipoErroEnum.INFRA;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		if (ex instanceof BusinessException) {
			status = HttpStatus.BAD_REQUEST;
			tipoErroEnum = TipoErroEnum.NEGOCIO;
		}

		final Fault fault = new Fault();
		fault.setTipoErro(tipoErroEnum);
		fault.setErros(new ArrayList<>());
		fault.getErros().add(ex.getMessage());

		return new ResponseEntity<>(fault, status);
	}
}
