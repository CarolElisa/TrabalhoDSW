<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Lista de vagas virtual</title>
</head>
<body>
	<div align="center">
		<h1>
			<fmt:message key="books.welcome" />
		</h1>
		<h2>
			<a href="/${sessionScope.contextPath}/empresas">
				<fmt:message key="books.entity" />
			</a>
			&nbsp;&nbsp;&nbsp;
			<a href="/${sessionScope.contextPath}/usuarios"> 
				<fmt:message key="users.entity" />
			</a> 
			&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/logout.jsp">
				<fmt:message key="exit.link" />
			</a>
			<br/>
			<br/>
			<a href="/${sessionScope.contextPath}/vagas/lista">
				<fmt:message key="books.list" />
			</a>
			<a href="/${sessionScope.contextPath}/vagas/cadastro">
				<fmt:message key="books.create" />
			</a>
		</h2>
		<h3><fmt:message key="books.list" /></h3>
		<br/>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Candidaturas</caption>
			<p>Tipo de Usuário: ${tipoUser}</p>
			<tr>
				<th>ID</th>
				<th>Funcao</th>
				<th>CPF</th>
				<th>Profissional</th>
				<th>Status</th>
				<th>Acões</th>
			</tr>
			<c:forEach var="candidatura" items="${requestScope.listaCandidaturas}">
				<tr>
					<td>${candidatura.vaga.id}</td>
					<td>${candidatura.vaga.funcao}</td>
					<td size="60">${candidatura.profissional.cpf}</td>
					<td size="60">${candidatura.profissional.nome}</td>
					<td>${candidatura.status}</td>
					<td>
						<c:choose>
							<c:when test="${tipoUser == 'admin'}">
								<a href="/${requestScope.contextPath}/vagas/edicao?id=${vaga.id}">Aprovar</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a
								href="/${requestScope.contextPath}/vagas/remocao?id=${vaga.id}"
								onclick="return confirm('Tem certeza de que deseja excluir este item?');">
									Recusar </a>
							</c:when>
							<c:when test="${tipoUser == 'empresa'}">
								<a href="/${requestScope.contextPath}/vagas/aprovar?id=${candidatura.vaga.id}&cpf=${candidatura.profissional.cpf}">Aprovar</a>
								<a href="/${requestScope.contextPath}/vagas/recusar?id=${candidatura.vaga.id}&cpf=${candidatura.profissional.cpf}">Recusar</a>
							</c:when>
							<c:when test="${tipoUser == 'funcionario'}">
								<a
								href="/${requestScope.contextPath}/vagas/edicao?id=${vaga.id}">Candidatar-se
									</a>
							</c:when>
						</c:choose>
					</td>


				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>