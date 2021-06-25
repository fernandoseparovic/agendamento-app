package com.magalu.logistica.app.agendamento;

import org.junit.runner.RunWith;

import com.magalu.logistica.app.config.AbstractTeste;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/agendamento.feature", 
				 glue = { "com.magalu.logistica.app.agendamento" })
public class AgendamentoApiIT extends AbstractTeste{

}
