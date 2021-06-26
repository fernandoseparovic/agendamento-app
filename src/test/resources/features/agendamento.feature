Feature: API permite agendar um envio de comunicacao

  @Scenario1
  Scenario Outline: Fluxo - busca agendamento.
    Given client has access to the /agendamento API
    When client calls GET agendamento with <idAgendamento>, <mensagem>, <dataCriacao>, <dataParaEnvio>, <page>, <perPage>
    Then client of agendamento receives status code of <status>
    Then client of GET agendamento receives body of <nomeArquivoResultadoEsperado>

    Examples: 
      | idAgendamento | mensagem |  dataCriacao | dataParaEnvio | page | perPage | status |          nomeArquivoResultadoEsperado |
      |            "" |       "" |           "" |            "" |   "" |      "" |    200 |  "buscaAgendamentoSemParametros.json" |
 	  |           "1" |       "" |           "" |            "" |   "" |      "" |    200 |          "buscaAgendamentoPorId.json" |
 	  |            "" | "teste2" |           "" |            "" |   "" |      "" |    200 |    "buscaAgendamentoPorMensagem.json" |
      |            "" |       "" | "19/08/2021" |            "" |   "" |      "" |    200 | "buscaAgendamentoPorDataCriacao.json" |
      
      
  @Scenario2
  Scenario Outline: Fluxo - agendar envio.
    Given client has access to the /agendamento API
    When client calls PUT agendamento with <nomeArquivoInserirAgendamento>, <status>
    Then client of agendamento receives status code of <status>
    Then client of PUT agendamento receives body of <nomeArquivoResultadoEsperado>
    
    Examples: 
      | nomeArquivoInserirAgendamento | status |      nomeArquivoResultadoEsperado |  
      |         "agendarEnvioOk.json" |    200 |    "resultadoAgendarEnvioOK.json" |


  @Scenario3
  Scenario Outline: Fluxo erro - agendar envio.
    Given client has access to the /agendamento API
    When client calls PUT agendamento with <nomeArquivoInserirAgendamento>, <status>
    Then client of agendamento receives status code of <status>
    Then client of PUT agendamento receives body of <nomeArquivoResultadoEsperado>
    
    Examples: 
      |        nomeArquivoInserirAgendamento | status |                  nomeArquivoResultadoEsperado |  
      | "agendarEnvioPessoaInexistente.json" |    400 | "resultadoAgendarEnvioPessoaInexistente.json" |
      

  @Scenario4
  Scenario Outline: Fluxo - deletar agendamento de envio.
    Given client has access to the /agendamento API
    When client calls DELETE agendamento with <idAgendamento>
    Then client of agendamento receives status code of <status>
    
    Examples: 
      | idAgendamento | status |
      |           "5" |    200 |
      
      
  @Scenario5
  Scenario Outline: Fluxo erro - deletar agendamento de envio.
    Given client has access to the /agendamento API
    When client calls DELETE agendamento with <idAgendamento>
    Then client of agendamento receives status code of <status>
    Then client of DELETE agendamento receives body of <nomeArquivoResultadoEsperado>
    
    Examples: 
      | idAgendamento | status |            nomeArquivoResultadoEsperado |  
      |           "5" |    400 | "deleteAgendamentoInexistenteErro.json" |
      
      