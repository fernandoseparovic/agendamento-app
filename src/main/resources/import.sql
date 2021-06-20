CREATE TABLE IF NOT EXISTS `logistica`.`canal_comunicacao` (
  `id_canal_comunicacao` INT NOT NULL COMMENT 'Identificador único do canal de comunicação',
  `descricao_canal_comunicacao` VARCHAR(60) NOT NULL COMMENT 'Descrição do canal de comunicação',
  PRIMARY KEY (`id_canal_comunicacao`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `logistica`.`pessoa` (
  `id_pessoa` INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único da pessoa',
  `nome_pessoa` VARCHAR(100) NOT NULL COMMENT 'Nome e sobrenome da pessoa',
  PRIMARY KEY (`id_pessoa`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `logistica`.`pessoa_canal_comunicacao` (
  `id_pessoa` INT NOT NULL COMMENT 'Identificador da pessoa',
  `id_canal_comunicacao` INT NOT NULL COMMENT 'Identificador do canal de comunicação',
  PRIMARY KEY (`id_canal_comunicacao`, `id_pessoa`),
  CONSTRAINT `id_pessoa_fk`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES `logistica`.`pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_canal_comunicacao_fk`
    FOREIGN KEY (`id_canal_comunicacao`)
    REFERENCES `logistica`.`canal_comunicacao` (`id_canal_comunicacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `logistica`.`agendamento` (
  `id_agendamento` INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de um agendamento',
  `mensagem` VARCHAR(300) NOT NULL COMMENT 'Mensagem a ser enviada deseja enviar para o(s) destinatarios ',
  `data_hora_criacao` DATETIME NOT NULL COMMENT 'Data e hora que o agendamento foi realizado',
  `data_hora_para_envio` DATETIME NOT NULL COMMENT 'Data hora para o envio da mensagem',
  PRIMARY KEY (`id_agendamento`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `logistica`.`status_comunicacao` (
  `id_status_comunicacao` INT NOT NULL COMMENT 'Identificador único de status de agendamento',
  `descricao_status_agendamento` VARCHAR(45) NOT NULL COMMENT 'Descrição do status de agendamento',
  PRIMARY KEY (`id_status_comunicacao`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `logistica`.`destinatario_comunicacao` (
  `id_agendamento` INT NOT NULL COMMENT 'Identificador do agendamento',
  `id_pessoa_destinatario` INT NOT NULL COMMENT 'Identificador do destinatário',
  `id_canal_comunicacao` INT NOT NULL COMMENT 'Identificador do canal de comunicação',
  `id_status_comunicacao` INT NOT NULL,
  PRIMARY KEY (`id_agendamento`, `id_pessoa_destinatario`, `id_canal_comunicacao`),
  INDEX `idCanalComunicacaoFK_idx` (`id_canal_comunicacao` ASC) VISIBLE,
  INDEX `idPessoaFK_idx` (`id_pessoa_destinatario` ASC) VISIBLE,
  INDEX `id_status_comunicacao_fk_idx` (`id_status_comunicacao` ASC) VISIBLE,
  CONSTRAINT `id_pessoa_destinatario_fk`
    FOREIGN KEY (`id_pessoa_destinatario`)
    REFERENCES `logistica`.`pessoa_canal_comunicacao` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_destinatario_canal_comunicacao_fk`
    FOREIGN KEY (`id_canal_comunicacao`)
    REFERENCES `logistica`.`pessoa_canal_comunicacao` (`id_canal_comunicacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_agendamento_fk`
    FOREIGN KEY (`id_agendamento`)
    REFERENCES `logistica`.`agendamento` (`id_agendamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_status_comunicacao_fk`
    FOREIGN KEY (`id_status_comunicacao`)
    REFERENCES `logistica`.`status_comunicacao` (`id_status_comunicacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio) 
	values ('mensagem teste1', '2021-06-19 12:00:00' , '2021-06-20 12:00:00');
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio) 
	values ('mensagem teste2', '2021-06-19 12:00:00' , '2021-06-20 12:00:00');


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

