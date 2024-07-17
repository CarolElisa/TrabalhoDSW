<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de vagas virtual</title>
</head>
<body>
	<div align="center">
		<h1>Gerenciamento de Vagas</h1>
		<h2>
			<a href="/${requestScope.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
			<a href="/${requestScope.contextPath}/vagas/cadastro">Adicione Nova Vaga</a>
			
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Vagas</caption>
			<tr>
				<th>ID</th>
				<th>Funcao</th>
				<th>Empresa</th>
				<th>Nivel</th>
				<th>Anos de Contrato</th>
				<th>Salario</th>
				<th>Acões</th>
			</tr>
			<c:forEach var="vaga" items="${requestScope.listaVagas}">
				<tr>
					<td>${vaga.id}</td>
					<td>${vaga.funcao}</td>
					<td size="60">${vaga.empresa.nome}</td>
					<td>${vaga.nivel}</td>
					<td>${vaga.anosContrato}</td>
					<td>${vaga.salario}</td>
					<td><a href="/${requestScope.contextPath}/vagas/edicao?id=${vaga.id}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/${requestScope.contextPath}/vagas/remocao?id=${vaga.id}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>