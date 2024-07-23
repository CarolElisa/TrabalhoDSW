drop database if exists DSW;

create database DSW;

use DSW;

create table Usuario(id bigint not null auto_increment, documento varchar(18) not null, nome varchar(256) not null, login varchar(256) not null unique, senha varchar(64) not null, papel varchar(10), primary key (id), UNIQUE (documento));

create table Empresa(cnpj varchar(18) not null, nome varchar(256) not null, descricao varchar(256), email varchar(256), senha varchar(256), cidade varchar(256), primary key (cnpj), foreign key (cnpj) references Usuario(documento));

create table Vaga(id bigint not null auto_increment, funcao varchar(256) not null, nivel varchar(256) not null, anosContrato integer not null, salario float not null, empresa_cnpj varchar(256), status varchar(256), primary key (id), foreign key (empresa_cnpj) references Empresa(cnpj));

create table Profissional(cpf varchar(14) not null, nome varchar(256) not null, email varchar(256) not null, senha varchar(16) not null, telefone varchar(13) not null, sexo varchar(256) not null, dataNasc varchar(10) not null, primary key (cpf), foreign key (cpf) references Usuario(documento));

create table Candidatura(id bigint not null auto_increment, cpf varchar(14) not null, id_vaga bigint not null not null, status varchar(256), primary key(id), foreign key(cpf) references Profissional(cpf), foreign key(id_vaga) references Vaga(id));


insert into Usuario(nome, documento, login, senha, papel) values ('Administrador', "admin", 'admin', 'admin', 'ADMIN');

insert into Usuario(nome, documento, login, senha, papel) values ('jose', '12.345.678/0008-99', 'jose', 'jose', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Tecnomagia', '12.456.111/0002-12', 'tecnomagia@gmail.com', 'magosunidos123', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Escola Fulaninho', '11.115.228/2228-11', 'escfulaninho@fulaninho.com', 'fulaninho@462', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Amazon', '21.739.316/0001-70', 'amazon@amazon.com', 'UmaSenhaSegur@2', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Padaria Guanabara', '85.424.990/0001-48', 'pguanabara@gmail.com', 'NomeDoDono@123', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('New York City', '42.271.979/0001-82', 'nycjournal@ny.com', 'Password@458754', 'EMPR');
insert into Usuario(nome, documento, login, senha, papel) values ('Juliano Lopes', '296.332.571-15', 'jlopes32@gmail.com', 'juliano44', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Alberto Solano', '466.545.384-55', 'solanoalberto@yahoo.com', 'assol1998', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Mariana Alves de Almeida Filho', '347.471.780-40', 'mariprincesa@hotmail.com', 'filhinhadamamae', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Amanda Roberta Gomes', '303.620.702-37', 'amandaroberta@gmail.com', 'casa@123', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Luiza da Silva', '049.103.538-10', 'lu@yahoo.com', 'boblindo#14', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Roberto Luis dos Santos', '310.754.187-71', 'rls70@hotmail.com', 'luiza35', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Fernando Lucas Almeida', '266.126.832-44', 'fernando14@hotmail.com', 'ferNando#13', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Julia Maria Soares', '735.824.734-57', 'jmsoares@estudante.ufscar.br', 'jmsoares22', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Carla Ribeiro', '731.560.383-26', 'carlarbr@gmail.com', 'calinha123', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Julio Cesar Coentro', '357.970.831-75', 'jcc02@yahoo.com', 'juliojulho', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Gabriel Cristiano Correia', '966.656.669-99', 'gabrielcristiano@hotmail.com', 'julialinda44', 'PROF');
insert into Usuario(nome, documento, login, senha, papel) values ('Maria Maia', '053.416.151-07', 'm.maia12@hotmail.com', 'mariazinha@123', 'PROF');
insert into Empresa(cnpj, nome, descricao, email, senha, cidade) values  ('21.739.316/0001-70', 'Amazon LTDA', 'Empresa de Tecnologia', 'amazon@amazon.com', 'UmaSenhaSegur@2', 'Manaus');
insert into Empresa(cnpj, nome, descricao, email, senha, cidade) values  ('85.424.990/0001-48', 'Padaria Guanabara', 'Padaria', 'pguanabara@gmail.com', 'NomeDoDono@123', 'Sao Carlos');
insert into Empresa(cnpj, nome, descricao, email, senha, cidade) values  ('42.271.979/0001-82', 'New York City', 'Journal', 'nycjournal@ny.com', 'Password@458754', 'New York');
insert into Empresa(cnpj, nome, descricao, email, senha, cidade) values  ('12.345.678/0008-99', 'Barbearia do Jose', 'Barbearia', 'josebarbearia@jbarbe.com', 'barbariajose123', 'Sao Jose do Rio Preto');
insert into Empresa(cnpj, nome, descricao, email, senha, cidade) values  ('12.456.111/0002-12', 'Tecnomagia', 'Empresa de Tecnologia', 'tecnomagia@gmail.com', 'magosunidos123', 'Santa Barbara do Oeste');
insert into Empresa(cnpj, nome, descricao, email, senha, cidade) values  ('11.115.228/2228-11', 'Escola Fulaninho', 'Escola de Ensino Fundamental', 'escfulaninho@fulaninho.com', 'fulaninho@462', 'Santa Rita do Passa Quatro');


insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('296.332.571-15', 'Juliano Lopes', 'jlopes32@gmail.com', 'juliano44', '62 93541-6545', 'Masculino', '23/12/1982');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('466.545.384-55', 'Alberto Solano', 'solanoalberto@yahoo.com', 'assol1998', '83 93412-5482', 'Masculino', '14/03/1998');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('347.471.780-40', 'Mariana Alves de Almeida Filho', 'mariprincesa@hotmail.com', 'filhinhadamamae', '51 93518-6525', 'Feminino', '13/08/1994');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('303.620.702-37', 'Amanda Roberta Gomes', 'amandaroberta@gmail.com', 'casa@123', '96 94521-8774', 'Feminino', '09/03/2002');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('049.103.538-10', 'Luiza da Silva', 'lu@yahoo.com', 'boblindo#14', '11 93541-3698', 'Feminino', '09/04/1972');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('310.754.187-71', 'Roberto Luis dos Santos', 'rls70@hotmail.com', 'luiza35', '27 93262-5545', 'Masculino', '08/06/1970');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('266.126.832-44', 'Fernando Lucas Almeida', 'fernando14@hotmail.com', 'ferNando#13', '92 95578-6455', 'Masculino', '31/07/1995');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('735.824.734-57', 'Julia Maria Soares', 'jmsoares@estudante.ufscar.br', 'jmsoares22', '91 99336-2424', 'Feminino', '22/08/2001');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('731.560.383-26', 'Carla Ribeiro', 'carlarbr@gmail.com', 'calinha123', '85 99855-6669', 'feminino', '23/09/1986');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('357.970.831-75', 'Julio Cesar Coentro', 'jcc02@yahoo.com', 'juliojulho', '66 99452-3314', 'masculino', '06/07/2000');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('966.656.669-99', 'Gabriel Cristiano Correia', 'gabrielcristiano@hotmail.com', 'julialinda44', '43 99225-4661', 'masculino', '13/09/2002');
insert into Profissional(cpf, nome, email, senha, telefone, sexo, datanasc) values ('053.416.151-07', 'Maria Maia', 'm.maia12@hotmail.com', 'mariazinha@123', '63 92210-4937', 'feminino', '03/01/1992');


insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Barbeiro', 'Avancado', 2, 2500, '12.345.678/0008-99');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Pleno', 2, 4500, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Senior', 4, 9000, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Desenvolvedor', 'Estagiario', 2, 1500, '12.456.111/0002-12');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Zelador', 'Basico', 1, 3200, '11.115.228/2228-11');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Analista de Qualidade', 'Pleno', '2', '5000', '21.739.316/0001-70');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Padeiro', 'Avancado', '10', '6000', '85.424.990/0001-48');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Atendente', 'Basico', '1', '1500', '85.424.990/0001-48');
insert into Vaga(funcao, nivel, anosContrato, salario, empresa_cnpj) values ('Photographer', 'Advanced', '4', '8000', '42.271.979/0001-82');

insert into Candidatura(id, cpf, id_vaga, status) values (1, '296.332.571-15', 1, "ABERTO");