# agendamento-app
Tem como objetivo prover funcionalidades para agendamento de envio de comunicações, para isso expõe serviços REST para manutenção das tabelas 
logistica.agendamento e logistica.destinatario_comunicacao.

A aplicação foi escrita utilizando spring boot, spring data jpa, maven para gerenciamento de dependências, banco de dados mySql, com servidor de aplicações undertown.
Para testes integrados foi utilizado cucumber, juntamente com banco h2, com os cenários de testes descritos no arquivo agendamento.feature
O banco h2 utiliza os arquivos schema.sql para criar as tabelas do banco em memória e o arquivo data.sql para realizar os inserts iniciais.
Para os testes unitários Mockito com Junit 4.

Spring validation para os objetos e atributos expostos nos serviços relacionados com o agendamento

OpenApi para documentação da Api onde é possível acessar: 

1) Documentação em formato Json
http://localhost:8080/api-docs

2) Documentação em formato .yaml (geralmente utilizado para testes no postman)
http://localhost:8080/api-docs.yaml

3) Documentação no formato swagger (onde é possível realizar testes diretamente no browser)
http://localhost:8080/swagger

SonarLint e FindBugs para averiguar possíveis problemas no código. 


## Geração entidades
Para gerar as entidades foi utilizando o hibernate tools onde: 
hibernate.reveng.cfg.xml - possui as configurações de acesso ao banco. 
hibernate.reveng.xml - possui a lista de tabelas das quais se deve fazer a engenharia reversa.



## Problemas conhecidos

1) Métodos anotados com @Transactional não devem ser chamados dentro da mesma classe que foram escritos, pois caso assim seja feito a transação não funciona.

2) O hibernate ao gerar as entidades com mais de um atributo na FK vindo de uma mesma tabela gera um objeto para cada atributo de FK. 
Por exemplo a entidade DestinatarioCanalComunicacao possui 2 atributos de FK vindos da tabela PessoaCanalComunicacao no caso o IdCanalComunicacao e IdPessoaDestinatario neste caso o hibernate gerou:

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_canal_comunicacao", nullable = false, insertable = false, updatable = false)
	private PessoaCanalComunicacao pessoaCanalComunicacaoByIdCanalComunicacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa_destinatario", nullable = false, insertable = false, updatable = false)
	private PessoaCanalComunicacao pessoaCanalComunicacaoByIdPessoaDestinatario;
	
Quando isso acontecer o correto é utilizar somente um objeto PessoaCanalComunicacao e nele mapear os dois atributos FKs. Fica desta forma 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_canal_comunicacao", nullable = false, insertable = false, updatable = false)
	@JoinColumn(name = "id_pessoa_destinatario", nullable = false, insertable = false, updatable = false)
	public PessoaCanalComunicacao getPessoaCanalComunicacao() {
		return pessoaCanalComunicacao;
	}
	
3) Para rodar os testes é necessário utilizar a versão 4 do Junit.
