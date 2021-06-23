CREATE DATABASE `logistica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE IF NOT EXISTS `logistica`.`canal_comunicacao` (
  `id_canal_comunicacao` int NOT NULL COMMENT 'Identificador unico do canal de comunicacao',
  `descricao_canal_comunicacao` varchar(60) NOT NULL COMMENT 'Descricao do canal de comunicacao',
  PRIMARY KEY (`id_canal_comunicacao`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `logistica`.`pessoa` (
  `id_pessoa` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador unico da pessoa',
  `nome_pessoa` varchar(100) NOT NULL COMMENT 'Nome e sobrenome da pessoa',
  PRIMARY KEY (`id_pessoa`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `logistica`.`pessoa_canal_comunicacao` (
  `id_pessoa` int NOT NULL COMMENT 'Identificador da pessoa',
  `id_canal_comunicacao` int NOT NULL COMMENT 'Identificador do canal de comunicacao',
  PRIMARY KEY (`id_canal_comunicacao`,`id_pessoa`),
  KEY `id_pessoa_fk` (`id_pessoa`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `logistica`.`status_comunicacao` (
  `id_status_comunicacao` int NOT NULL COMMENT 'Identificador unico de status de agendamento',
  `descricao_status_agendamento` varchar(45) NOT NULL COMMENT 'Descricao do status de agendamento',
  PRIMARY KEY (`id_status_comunicacao`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `logistica`.`agendamento` (
  `id_agendamento` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador unico de um agendamento',
  `mensagem` varchar(300) NOT NULL COMMENT 'Mensagem a ser enviada deseja enviar para o(s) destinatarios ',
  `data_hora_criacao` datetime NOT NULL COMMENT 'Data e hora que o agendamento foi realizado',
  `data_hora_para_envio` datetime NOT NULL COMMENT 'Data hora para o envio da mensagem',
  `id_status_comunicacao` int NOT NULL,
  PRIMARY KEY (`id_agendamento`),
  KEY `id_status_comunicacao_fk_idx` (`id_status_comunicacao`),
  CONSTRAINT `id_agendamento_status_fk` FOREIGN KEY (`id_status_comunicacao`) REFERENCES `status_comunicacao` (`id_status_comunicacao`)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `logistica`.`destinatario_comunicacao` (
  `id_agendamento` int NOT NULL COMMENT 'Identificador do agendamento',
  `id_pessoa_destinatario` int NOT NULL COMMENT 'Identificador do destinatario',
  `id_canal_comunicacao` int NOT NULL COMMENT 'Identificador do canal de comunicacao',
  `id_status_comunicacao` int NOT NULL,
  PRIMARY KEY (`id_agendamento`,`id_pessoa_destinatario`,`id_canal_comunicacao`),
  KEY `idCanalComunicacaoFK_idx` (`id_canal_comunicacao`),
  KEY `idPessoaFK_idx` (`id_pessoa_destinatario`),
  KEY `id_status_comunicacao_fk_idx` (`id_status_comunicacao`),
  CONSTRAINT `id_agendamento_fk` FOREIGN KEY (`id_agendamento`) REFERENCES `agendamento` (`id_agendamento`),
  CONSTRAINT `id_destinatario_canal_comunicacao_fk` FOREIGN KEY (`id_canal_comunicacao`) REFERENCES `pessoa_canal_comunicacao` (`id_canal_comunicacao`),
  CONSTRAINT `id_pessoa_destinatario_fk` FOREIGN KEY (`id_pessoa_destinatario`) REFERENCES `pessoa_canal_comunicacao` (`id_pessoa`),
  CONSTRAINT `id_status_comunicacao_fk` FOREIGN KEY (`id_status_comunicacao`) REFERENCES `status_comunicacao` (`id_status_comunicacao`)
) ENGINE = InnoDB;


# status comunicacao
insert into logistica.status_comunicacao (id_status_comunicacao, descricao_status_agendamento) values (1, 'Aguardando envio');
insert into logistica.status_comunicacao (id_status_comunicacao, descricao_status_agendamento) values (2, 'Enviado');
insert into logistica.status_comunicacao (id_status_comunicacao, descricao_status_agendamento) values (3, 'Erro ao enviar');


# canal comunicação
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (1, 'Email');
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (2, 'Sms');
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (3, 'Push');  
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (4, 'Whatsapp');


# pessoa
insert into logistica.pessoa (nome_pessoa) values ('Fernando Separovic');
insert into logistica.pessoa (nome_pessoa) values ('Jose da Silva');
insert into logistica.pessoa (nome_pessoa) values ('Roberto Souza');


# pessoa canal comunicacao

# Fernando Separovic - Email
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (1 , 1);

# Fernando Separovic - Sms
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (1 , 2);

# Jose da Silva - Push
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (2 , 3);

# Roberto Souza
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (3 , 4);


# agendamento
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio, id_status_comunicacao) 
	values ('mensagem teste1', '2021-06-19 12:00:00' , '2021-06-20 12:00:00', 1);
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio, id_status_comunicacao)  
	values ('mensagem teste2', '2021-06-19 12:00:00' , '2021-06-20 12:00:00', 1);


# destinatario comunicacao

# agendamento 1 (mensagem teste1), destinatario 2 (Jose da Silva), canal 3 (Push), status 1 (Aguardando envio)
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (1, 2, 3, 1);
    
# agendamento 1 (mensagem teste1), destinatario 3 (Roberto Souza), canal 4 (Whatsapp), status 1 (Aguardando envio)    
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (1, 3, 4, 1);

# agendamento 2 (mensagem teste2), destinatario 1 (Fernando Separovic), canal 1 (Email), status 1 (Aguardando envio)    
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (2, 1, 1, 1);

# agendamento 2 (mensagem teste2), destinatario 1 (Fernando Separovic), canal 2 (Sms), status 1 (Aguardando envio)
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (2, 1, 2, 1);

