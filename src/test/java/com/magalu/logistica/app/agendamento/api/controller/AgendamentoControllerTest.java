package com.magalu.logistica.app.agendamento.api.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.magalu.logistica.app.agendamento.api.exception.BusinessException;
import com.magalu.logistica.app.agendamento.api.exception.InfraException;
import com.magalu.logistica.app.agendamento.api.facade.AgendamentoFacade;
import com.magalu.logistica.app.mock.SolicitacaoAgendamentoMock;

/**
 * Esta classe de testes tem como objetivo verificar se o controler esta tratando erros de infra e de negocio
 * 
 * @author Fernando Separovic
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AgendamentoControllerTest {

	@InjectMocks
	@Spy
	private AgendamentoController agendamentoController;

	@Mock
	private AgendamentoFacade agendamentoFacade;


	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	

	@Test
	public void agendarEnvioTestErroInesperado() throws BusinessException {
		Mockito.when(agendamentoFacade.agendarEnvio(Mockito.any()))
			   .thenThrow(new NullPointerException("Teste agendarEnvio Erro Infra"));
		
		Assert.assertThrows(InfraException.class, 
							() -> agendamentoController.agendarEnvio(SolicitacaoAgendamentoMock.solicitacaoValida()));
	}
	
	@Test
	public void agendarEnvioTestErroNegocio() throws BusinessException {
		Mockito.when(agendamentoFacade.agendarEnvio(Mockito.any()))
			   .thenThrow(new BusinessException("Teste agendarEnvio Erro Negocio"));
		
		Assert.assertThrows(BusinessException.class, 
							() -> agendamentoController.agendarEnvio(SolicitacaoAgendamentoMock.solicitacaoValida()));
	}
	
	@Test
	public void consultarAgendamentoTestErroInesperado() throws BusinessException {
		Mockito.when(agendamentoFacade.buscarAgendamento(Mockito.any()))
			   .thenThrow(new NullPointerException("Teste consultarAgendamento Erro Infra"));
		
		Assert.assertThrows(InfraException.class, 
							() -> agendamentoController.consultarAgendamento(null, null, null, null, null, null));
	}


	@Test
	public void deletarAgendamentoTestErroInesperado() throws BusinessException {
		Mockito.doThrow(new NullPointerException("Teste deletarAgendamento Erro Infra"))
			   .when(agendamentoFacade)
			   .deletarAgendamento(Mockito.any());

		Assert.assertThrows(InfraException.class, () -> agendamentoController.deletarAgendamento(1));
	}
	
	@Test
	public void deletarAgendamentoTestErroNegocio() throws BusinessException {
		Mockito.doThrow(new BusinessException("Teste deletarAgendamento Erro Negocio"))
			   .when(agendamentoFacade)
			   .deletarAgendamento(Mockito.any());

		Assert.assertThrows(BusinessException.class, () -> agendamentoController.deletarAgendamento(2));
	}
	
}
