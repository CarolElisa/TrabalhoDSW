drop database if exists DSW;

create database DSW;

use DSW;

create table Usuario(id bigint not null auto_increment, documento varchar(18) not null, nome varchar(256) not null, login varchar(20) not null unique, senha varchar(64) not null, papel varchar(10), primary key (id), UNIQUE (documento));

create table Empresa(cnpj varchar(18) not null, nome varchar(256) not null, descricao varchar(256), email varchar(256), senha varchar(256), cidade varchar(256), primary key (cnpj), foreign key (cnpj) references Usuario(documento));

create table Vaga(id bigint not null auto_increment, funcao varchar(256) not null, nivel varchar(256) not null, anosContrato integer not null, salario float not null, empresa_cnpj varchar(256), primary key (id), foreign key (empresa_cnpj) references Empresa(cnpj));

create table Profissional(cpf varchar(14) not null, nome varchar(256) not null, email varchar(256) not null, senha varchar(16) not null, telefone varchar(10) not null, sexo varchar(256) not null, dataNasc varchar(10) not null, primary key (cpf), foreign key (cpf) references Usuario(documento));


insert into Usuario(nome, documento, login, senha, papel) values ('Administrador', "admin", 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, documento, login, senha, papel) values ('Jose', '12.345.678/0008-99', 'jose', 'jose', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Tecno', '12.456.111/0002-12', 'tech', 'tech', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Fula', '11.115.228/2228-11', 'fula', 'fula', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Funcionario', '123.456.789-4', 'func', 'func', 'FUNC');



insert into Empresa(cnpj, nome) values  ('12.345.678/0008-99', 'Barbearia do Jose');
insert into Empresa(cnpj, nome) values  ('12.456.111/0002-12', 'Tecnomagia');
insert into Empresa(cnpj, nome) values  ('11.115.228/2228-11', 'Escola Fulaninho');

insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Barbeiro', 'Avançado', 2, 2500, '12.345.678/0008-99');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Pleno', 2, 4500, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Senior', 4, 9000, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Estagiario', 2, 1500, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Zelador', 'Básico', 1, 3200, '11.115.228/2228-11');
