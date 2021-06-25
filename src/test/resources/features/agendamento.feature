Feature: API permite agendar um envio de comunicacao

  @Scenario1
  Scenario Outline: Fluxo principal - busca agendamento.
    Given client has access to the /agendamento API
    When client calls GET agendamento with <idAgendamento>, <mensagem>, <dataCriacao>, <dataParaEnvio>, <page>, <perPage>
    Then client of agendamento receives status code of <status>
    Then client of agendamento receives body of <nomeArquivoResultado>

    Examples: 
      | idAgendamento | mensagem | dataCriacao | dataParaEnvio | page | perPage | status | nomeArquivoResultado                 |
      |            "" |       "" |          "" |            "" |   "" |      "" |    200 | "buscaAgendamentoSemParametros.json" |

      