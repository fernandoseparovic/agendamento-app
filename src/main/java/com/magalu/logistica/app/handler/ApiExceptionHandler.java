package com.magalu.logistica.app.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.magalu.logistica.app.agendamento.api.exception.BusinessException;

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
		
		final Map<String, String> errors = ex.getBindingResult()
											 .getAllErrors()
											 .stream()
											 .collect(Collectors.toMap(error -> ((FieldError) error).getField(), 
													 				   ObjectError::getDefaultMessage));
		return new ResponseEntity<>(errors, status);
	}


	@ExceptionHandler({ BusinessException.class })
	public final ResponseEntity<Object> handleBusinessException(final BusinessException ex, 
																final WebRequest request) {
		
		final Map<String, String> errors = new HashMap<>();
		errors.put("Erro negocio: ", ex.getMessage());
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
