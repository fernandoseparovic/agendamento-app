package com.magalu.logistica.app.mock;

import java.util.Date;
import java.util.HashSet;

import org.apache.commons.lang3.time.DateUtils;

import com.magalu.logistica.app.agendamento.api.enuns.CanalComunicacaoEnum;
import com.magalu.logistica.app.agendamento.api.model.Destinatario;
import com.magalu.logistica.app.agendamento.api.model.SolicitacaoAgendamento;

public class SolicitacaoAgendamentoMock {

	public static SolicitacaoAgendamento solicitacaoValida() {
		final SolicitacaoAgendamento solicitacaoAgendamento = new SolicitacaoAgendamento();
		solicitacaoAgendamento.setDataHoraParaEnvio(DateUtils.addDays(new Date(), 2));
		solicitacaoAgendamento.setMensagem("Teste mensagem");
		solicitacaoAgendamento.setDestinatarios(new HashSet<>());
		solicitacaoAgendamento.getDestinatarios().add(
				new Destinatario().idPessoaDestinatario(1).canalComunicacao(CanalComunicacaoEnum.EMAIL));
		return solicitacaoAgendamento;
	}
}
