
CREATE SCHEMA IF NOT EXISTS logistica;


CREATE TABLE IF NOT EXISTS logistica.canal_comunicacao (
  id_canal_comunicacao int NOT NULL COMMENT 'Identificador unico do canal de comunicacao',
  descricao_canal_comunicacao varchar(60) NOT NULL COMMENT 'Descricao do canal de comunicacao',
  PRIMARY KEY (id_canal_comunicacao)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS logistica.pessoa (
  id_pessoa int NOT NULL AUTO_INCREMENT COMMENT 'Identificador unico da pessoa',
  nome_pessoa varchar(100) NOT NULL COMMENT 'Nome e sobrenome da pessoa',
  PRIMARY KEY (id_pessoa)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS logistica.pessoa_canal_comunicacao (
  id_pessoa int NOT NULL COMMENT 'Identificador da pessoa',
  id_canal_comunicacao int NOT NULL COMMENT 'Identificador do canal de comunicacao',
  PRIMARY KEY (id_canal_comunicacao,id_pessoa),
  KEY id_pessoa_fk (id_pessoa)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS logistica.status_comunicacao (
  id_status_comunicacao int NOT NULL COMMENT 'Identificador unico de status de agendamento',
  descricao_status_agendamento varchar(45) NOT NULL COMMENT 'Descricao do status de agendamento',
  PRIMARY KEY (id_status_comunicacao)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS logistica.agendamento (
  id_agendamento int NOT NULL AUTO_INCREMENT COMMENT 'Identificador unico de um agendamento',
  mensagem varchar(300) NOT NULL COMMENT 'Mensagem a ser enviada deseja enviar para o(s) destinatarios ',
  data_hora_criacao datetime NOT NULL COMMENT 'Data e hora que o agendamento foi realizado',
  data_hora_para_envio datetime NOT NULL COMMENT 'Data hora para o envio da mensagem',
  id_status_comunicacao int NOT NULL,
  PRIMARY KEY (id_agendamento),
  KEY id_agendamento_status_comunicacao_fk_idx (id_status_comunicacao),
  CONSTRAINT id_agendamento_status_fk FOREIGN KEY (id_status_comunicacao) REFERENCES status_comunicacao (id_status_comunicacao)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS logistica.destinatario_comunicacao (
  id_agendamento int NOT NULL COMMENT 'Identificador do agendamento',
  id_pessoa_destinatario int NOT NULL COMMENT 'Identificador do destinatario',
  id_canal_comunicacao int NOT NULL COMMENT 'Identificador do canal de comunicacao',
  id_status_comunicacao int NOT NULL,
  PRIMARY KEY (id_agendamento,id_pessoa_destinatario,id_canal_comunicacao),
  KEY idCanalComunicacaoFK_idx (id_canal_comunicacao),
  KEY idPessoaFK_idx (id_pessoa_destinatario),
  KEY id_status_comunicacao_fk_idx (id_status_comunicacao),
  CONSTRAINT id_agendamento_fk FOREIGN KEY (id_agendamento) REFERENCES agendamento (id_agendamento),
  CONSTRAINT id_destinatario_canal_comunicacao_fk FOREIGN KEY (id_canal_comunicacao) REFERENCES pessoa_canal_comunicacao (id_canal_comunicacao),
  CONSTRAINT id_pessoa_destinatario_fk FOREIGN KEY (id_pessoa_destinatario) REFERENCES pessoa_canal_comunicacao (id_pessoa),
  CONSTRAINT id_status_comunicacao_fk FOREIGN KEY (id_status_comunicacao) REFERENCES status_comunicacao (id_status_comunicacao)
) ENGINE = InnoDB;
