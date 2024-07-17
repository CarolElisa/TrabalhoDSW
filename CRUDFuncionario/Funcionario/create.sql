drop database if exists T5;

create database T5;

use T5;

create table Empresa(cnpj varchar(18) not null, nome varchar(256) not null, email varchar(256), senha varchar(256), descricao varchar(256), cidade varchar(256), primary key (cnpj));

create table Vaga(id bigint not null auto_increment, funcao varchar(256) not null, nivel varchar(256) not null, anosContrato integer not null, salario float not null, empresa_cnpj varchar(18) not null, primary key (id), foreign key (empresa_cnpj) references Empresa(cnpj));

insert into Empresa(cnpj, nome, descricao) values  ('12.345.678/0008-99', 'Barbearia do Jose', 'A melhor barbearia da cidade');
insert into Empresa(cnpj, nome, descricao) values  ('12.456.111/0002-12', 'Tecnomagia', 'Empresa de tecnologia');
insert into Empresa(cnpj, nome) values  ('11.115.228/2228-11', 'Escola Fulaninho');

insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Barbeiro', 'Avançado', 2, 2500, '12.345.678/0008-99');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Pleno', 2, 4500, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Senior', 4, 9000, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Estagiario', 2, 1500, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Zelador', 'Básico', 1, 3200, '11.115.228/2228-11');
