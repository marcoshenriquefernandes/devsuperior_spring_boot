insert into tb_categoria(descricao) values ('Curso');
insert into tb_categoria(descricao) values ('Oficina');

insert into tb_participante(email, nome) values ('jose@gmail.com', 'José Silva');
insert into tb_participante(email, nome) values ('tiago@gmail.com', 'Tiago Faria');
insert into tb_participante(email, nome) values ('maria@gmail.com', 'Maria do Rosário');
insert into tb_participante(email, nome) values ('teresa@gmail.com', 'Teresa Silva');

insert into tb_atividade(nome, descricao, preco, categoria_id) values ('Curso de HTML', 'Aprenda HTML de forma prática', 80.00, 1);
insert into tb_atividade(nome, descricao, preco, categoria_id) values ('Oficina de Github', 'Controle versões de seus projetos', 50.00, 2);

insert into tb_bloco(inicio, fim, atividade_id) values (TIMESTAMP WITH TIME ZONE '2017-09-25T08:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-25T11:00:00Z', 1);
insert into tb_bloco(inicio, fim, atividade_id) values (TIMESTAMP WITH TIME ZONE '2017-09-25T14:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-25T18:00:00Z', 2);
insert into tb_bloco(inicio, fim, atividade_id) values (TIMESTAMP WITH TIME ZONE '2017-09-26T08:00:00Z', TIMESTAMP WITH TIME ZONE '2017-09-26T11:00:00Z', 2);

insert into tb_atividade_participante(atividade_id, participante_id) values (1, 1);
insert into tb_atividade_participante(atividade_id, participante_id) values (1, 2);
insert into tb_atividade_participante(atividade_id, participante_id) values (1, 3);
insert into tb_atividade_participante(atividade_id, participante_id) values (2, 1);
insert into tb_atividade_participante(atividade_id, participante_id) values (2, 3);
insert into tb_atividade_participante(atividade_id, participante_id) values (2, 4);