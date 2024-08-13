# TrabalhoDSW

Documento de requisitos: 
     
R1: -> Implementado: Caroline 33%, Leandro 33%, Mauricio 33%
CRUD 1 de profissionais (requer login de administrador) 


R2: -> Implementado: Caroline 33%, Leandro 33%, Mauricio 33%
CRUD de empresas (requer login de administrador) 

R3: -> Implementado: Caroline 33%, Leandro 33%, Mauricio 33%
Cadastro de vagas de estágio/trabalho (requer login da empresa via e-mail + senha).
Depois de fazer login, a empresa pode cadastrar uma vaga de estágio/trabalho. O cadastro
de vagas deve possuir os seguintes dados: CNPJ da empresa, descrição (perfil profissional,  
habilidades desejadas etc), remuneração e data limite de inscrição. O período de
candidaturas (processo seletivo) para essa vaga encerra-se na data limite de inscrição.  

R4: -> Implementado: Caroline 33%, Leandro 33%, Mauricio 33%
Listagem de todas as vagas (em aberto) em uma única página (não requer login). O   
sistema deve prover a funcionalidade de filtrar as vagas (em aberto) por cidade

R5: -> Parcialmente implementado: Caroline 33%, Leandro 33%, Mauricio 33%
Candidatura a vaga de estágio/trabalho (requer login do profissional via e-mail + senha).
Ao clicar em uma vaga (requisito R4), o profissional pode se candidatar à vaga. Nesse caso, é
necessário que ele apresente suas qualificações para a vaga -- pode ser através do upload de    
seu currículo em formato PDF. O sistema deve restrigir que cada candidato se candidate
apenas uma vez à cada vaga.

R6:  -> Implementado: Caroline 33%, Leandro 33%, Mauricio 33%
Listagem de todas as vagas de uma empresa (requer login da empresa via e-mail +
senha). Depois de fazer login, a empresa pode visualizar todas suas vagas cadastradas.    

R7: -> Parcialmente implementado: Caroline 33%, Leandro 33%, Mauricio 33%
Listagem de todas as candidaturas de um profissional (requer login do profissional via email + senha).
Depois de fazer login, o profissional pode visualizar todas suas candidaturas
cadastradas com seus respectivos status.
ABERTO indica que a data limite de inscrição ainda não se encerrou ou ainda encontrase em fase de análise pela empresa (requisito R8).    
NÃO SELECIONADO indica que a
empresa considera que o perfil do profissional não se adequa à vaga. ENTREVISTA
indica que o candidato foi (ou será) chamado para uma entrevista.

R8: -> Parcialmente implementado: Caroline 33%, Leandro 33%, Mauricio 33%
Ao término do período de inscrição, inicia-se a fase de análise. A empresa (requer login
da empresa via e-mail + senha), para cada candidato, deve analisar as suas qualificações
(currículo) e atualizar o status da candidatura para NÃO SELECIONADO ou ENTREVISTA. Nos
dois casos, o candidato deve ser informado (via e-mail) sobre a decisão. No caso do status      
ENTREVISTA, a empresa deve também informar um horário para uma entrevista (via
videoconferência) com o candidato -- o link da videoconferência (google meet, zoom, etc)
deve estar presente no corpo da mensagem enviada.

R9: -> Parcialmente implementado: Caroline 33%, Leandro 33%, Mauricio 33%
O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro
de sua escolha.


---------------------------------------------------------------------------------------------

Roteiro de execução:

Rodar o arquivo create.sql para gerar o banco de dados.
Tabelas geradas:
      Usuario -> Cadastro geral dos usuários
      Emrpesa -> Atributos especificos para usuarios do tipo empresa
      Profissional -> Atibutos especificos para usuarios do tipo profissional
      Vaga -> Cadastro de vaga (pode ser gerado por uma empresa, ou por um admin
      Candidatura -> Mostra os candidatos que se candidataram para as vagas

Todas as tabelas foram populadas.
Para testar, recomenda-se utilizar os seguintes logins/senha:
Para administradores: admin/admin
Para empresa: jose/jose
Para Profissional: prof/prof

