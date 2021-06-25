
-- status comunicacao
insert into logistica.status_comunicacao (id_status_comunicacao, descricao_status_agendamento) values (1, 'Aguardando envio');
insert into logistica.status_comunicacao (id_status_comunicacao, descricao_status_agendamento) values (2, 'Enviado');
insert into logistica.status_comunicacao (id_status_comunicacao, descricao_status_agendamento) values (3, 'Erro ao enviar');


-- canal comunicacao
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (1, 'Email');
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (2, 'Sms');
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (3, 'Push');  
insert into logistica.canal_comunicacao (id_canal_comunicacao, descricao_canal_comunicacao) values (4, 'Whatsapp');


-- pessoa
insert into logistica.pessoa (nome_pessoa) values ('Fernando Separovic');
insert into logistica.pessoa (nome_pessoa) values ('Jose da Silva');
insert into logistica.pessoa (nome_pessoa) values ('Roberto Souza');


-- pessoa canal comunicacao

-- Fernando Separovic - Email
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (1 , 1);

-- Fernando Separovic - Sms
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (1 , 2);

-- Jose da Silva - Push
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (2 , 3);

-- Roberto Souza
insert into logistica.pessoa_canal_comunicacao (id_pessoa, id_canal_comunicacao) values (3 , 4);


-- agendamento
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio, id_status_comunicacao) 
	values ('mensagem teste1', '2021-06-19 12:00:00' , '2021-06-20 12:00:00', 1);
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio, id_status_comunicacao)  
	values ('mensagem teste2', '2021-07-19 12:00:00' , '2021-07-20 12:00:00', 1);
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio, id_status_comunicacao) 
	values ('mensagem teste3', '2021-08-19 12:00:00' , '2021-08-20 12:00:00', 1);
insert into logistica.agendamento (mensagem, data_hora_criacao, data_hora_para_envio, id_status_comunicacao)  
	values ('mensagem teste4', '2021-09-19 12:00:00' , '2021-10-20 12:00:00', 1);


-- destinatario comunicacao

-- agendamento 1 (mensagem teste1), destinatario 2 (Jose da Silva), canal 3 (Push), status 1 (Aguardando envio)
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (1, 2, 3, 1);
    
-- agendamento 1 (mensagem teste1), destinatario 3 (Roberto Souza), canal 4 (Whatsapp), status 1 (Aguardando envio)    
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (1, 3, 4, 1);

-- agendamento 2 (mensagem teste2), destinatario 1 (Fernando Separovic), canal 1 (Email), status 1 (Aguardando envio)    
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (2, 1, 1, 1);

-- agendamento 2 (mensagem teste2), destinatario 1 (Fernando Separovic), canal 2 (Sms), status 1 (Aguardando envio)
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (2, 1, 2, 1);
	
-- agendamento 3 (mensagem teste3), destinatario 1 (Fernando Separovic), canal 2 (Sms), status 1 (Aguardando envio)
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (3, 1, 2, 1);
	
-- agendamento 4 (mensagem teste4), destinatario 1 (Fernando Separovic), canal 2 (Sms), status 1 (Aguardando envio)
insert into logistica.destinatario_comunicacao (id_agendamento, id_pessoa_destinatario, id_canal_comunicacao, id_status_comunicacao)
	values (4, 1, 2, 1);

