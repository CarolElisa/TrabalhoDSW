<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Lista de empresas virtual</title>
</head>
<body>
	<div align="center">
		<h1>Gerenciamento de Empresas</h1>
		<h2>
			<a href="/${requestScope.contextPath}">Menu Principal</a> &nbsp;&nbsp;&nbsp; 
			<a href="/${requestScope.contextPath}/empresas/cadastro">Adicione Nova Empresa</a>
		</h2>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Empresas</caption>
			<tr>
				<th>Id_user</th>
				<th>CNPJ</th>
				<th>Nome</th>
				<th>Descricao</th>
				<th>E-mail</th>
				<th>Senha</th>
				<th>Cidade</th>
				<th>Acões</th>
			</tr>
			<c:forEach var="empresa" items="${requestScope.listaEmpresas}">
				<tr>
					<td>${empresa.id}</td>
					<td>${empresa.cnpj}</td>
					<td>${empresa.nome}</td>
					<td size="60">${empresa.descricao}</td>
					<td>${empresa.email}</td>
					<td>${empresa.senha}</td>
					<td>${empresa.cidade}</td>
					<td><a href="/${requestScope.contextPath}/empresas/edicao?cnpj=${empresa.cnpj}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/${requestScope.contextPath}/empresas/remocao?cnpj=${empresa.cnpj}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>