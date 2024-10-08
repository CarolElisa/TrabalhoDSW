<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">	
<head>
<title>Lista de vagas virtual</title>
</head>
<body>
	<div align="center">
		<h1>
			<fmt:message key="vaga.welcome" />
		</h1>



		<h2>
			<c:choose>
				<c:when test="${tipoUser == 'admin'}">
					<a href="/${sessionScope.contextPath}/empresas">
						<fmt:message key="empresa.entity" />
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="/${sessionScope.contextPath}/usuarios"> 
						<fmt:message key="user.entity" />
					</a> 
					&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/vagas.jsp">
						<fmt:message key="vaga.link" />
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/logout.jsp">
						<fmt:message key="exit.link" />
					</a>
					<br/>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${tipoUser == 'empresa'}"> 
					
					<br/>
					<a href="/${sessionScope.contextPath}/vagas/cadastro">
						<fmt:message key="vaga.create" />
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/logout.jsp">
						<fmt:message key="exit.link" />
					</a>
					<br/>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${tipoUser == 'profissional'}"> 
					<a href="${pageContext.request.contextPath}/vagas/listaCandProf">
						<fmt:message key="prof.candidatura" />
					</a>
					&nbsp;&nbsp;&nbsp;
					<br/>
					<a href="${pageContext.request.contextPath}/logout.jsp">
						<fmt:message key="exit.link" />
					</a>
				</c:when>
		</c:choose>
		</h2>
		<h3><fmt:message key="vaga.list" /></h3>
		<br/>
	</div>

	<div align="center">
		<table border="1">
			<caption>Lista de Vagas</caption>
			<p>Tipo de Usuário: ${tipoUser}</p>
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
					<td size="60">${vaga.empresaNome}</td>
					<td>${vaga.nivel}</td>
					<td>${vaga.anosContrato}</td>
					<td>${vaga.salario}</td>

					<td>
						<c:choose>
							<c:when test="${tipoUser == 'admin'}">
								<a href="/${requestScope.contextPath}/vagas/edicao?id=${vaga.id}">Edição</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a
								href="/${requestScope.contextPath}/vagas/remocao?id=${vaga.id}"
								onclick="return confirm('Tem certeza de que deseja excluir este item?');">
									Remoção </a>
							</c:when>
							<c:when test="${tipoUser == 'empresa'}">
								<a
								href="/${requestScope.contextPath}/vagas/edicao?id=${vaga.id}">Editar
									</a>
									<a
								href="/${requestScope.contextPath}/vagas/candidatura?id_vaga=${vaga.id}">Analisar
									</a>
									<a
								href="/${requestScope.contextPath}/vagas/remocao?id=${vaga.id}"
								onclick="return confirm('Tem certeza de que deseja excluir este item?');">
									Remover </a>
							</c:when>
							<c:when test="${tipoUser == 'profissional'}">
								<a
								href="/${requestScope.contextPath}/vagas/candidatarse?id_vaga=${vaga.id}">Candidatar-se
									</a>
							</c:when>
						</c:choose>
					</td>


				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</fmt:bundle>
</html>