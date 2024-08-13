<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Pagina do Profissional</title>
</head>
<body>
	<div align="center">
		<h1>Profissionais</h1>
		<h2>
			<a href="/${requestScope.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
			<a href="/${requestScope.contextPath}/profissionais/cadastro">Adicione Novo Profissional</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Profissionais</caption>
			<tr>
				<th>CPF</th>
				<th>Telefone</th>
				<th>Sexo</th>
				<th>Data de Nascimento</th>
			</tr>
			<c:forEach var="profissional" items="${requestScope.listaProfissional}">
				<tr>
					<td>${profissional.cpf}</td>
					<td>${profissional.telefone}</td>
					<td>${profissional.sexo}</td>
					<td>${profissional.dataDeNascimento}</td>
					<td><a href="/${requestScope.contextPath}/profissionais/edicao?cpf=${profissional.cpf}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/${requestScope.contextPath}/profissionais/remocao?cpf=${profissional.cpf}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>