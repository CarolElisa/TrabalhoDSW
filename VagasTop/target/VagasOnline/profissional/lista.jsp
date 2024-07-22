<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Profissionais</title>
</head>
<body>
	<div align="center">
		<h1>Gerenciamento de Profissionais</h1>
		<h2>
			<a href="/${requestScope.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
			<a href="/${requestScope.contextPath}/profissionais/cadastro">Adicione Novo Profissional</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Profissionais</caption>
			<tr>
				<th>Id_user</th>
				<th>CPF</th>
				<th>Nome</th>
				<th>E-mail</th>
				<th>Senha</th>
				<th>Telefone</th>
				<th>Sexo</th>
				<th>Data de Nascimento</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissionais}">
				<tr>

					<td>${profissional.id}</td>
					<td>${profissional.cpf}</td>
					<td>${profissional.nome}</td>
					<td>${profissional.email}</td>
					<td>${profissional.senha}</td>
					<td>${profissional.telefone}</td>
					<td>${profissional.sexo}</td>
					<td>${profissional.datanasc}</td>
					<td><a href="/${requestScope.contextPath}/profissionais/edicao?cpf=${profissional.cpf}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/${requestScope.contextPath}/profissionais/remocao?cpf=${profissional.cpf}"
						onclick="return confirm('Tem certeza de que deseja excluir este profissional?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>