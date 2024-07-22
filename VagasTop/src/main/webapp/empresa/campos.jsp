<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${empresa.nome == null}">
				Cadastro
            </c:when>
			<c:otherwise>
                Edicao
            </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${empresa != null}">
		<input type="hidden" name="usuId" value="${usuario.id}" />
	</c:if>
	<tr>
		<td><label for="nome"><fmt:message key="user.name" />
		</label></td>
		<td><input type="text" name="usuNome" size="45" required
			value="<c:out value='${usuario.nome}' />" /></td>
	</tr>
	<tr>
		<td><label for="login"><fmt:message key="user.login" />
		</label></td>
		<td><input type="text" name="usuLogin" size="20" required
			value="<c:out value='${usuario.login}' />" /></td>
	</tr>
	<tr>
		<td><label for="senha"><fmt:message key="user.password" />
		</label></td>
		<td><input type="text" name="usuSenha" size="20" required
			value="<c:out value='${usuario.senha}' />" /></td>
	</tr>
	<tr>
		<td><label for="papel"><fmt:message key="user.role" />
		</label></td>
		<td>
			<select name="usuPapel">
				<option value="EMPR" ${usuario.papel == "EMPR" ? 'selected="selected"' : ''}>EMPR</option>
			</select>			
		</td>
	</tr>
	<tr>

		<tr>
			<td><label for="cnpj">CNPJ</label></td>
			<td><input type="text" id="cnpj" name="cnpj" size="45"
				required value="${empresa.cnpj}" /></td>
		</tr>
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="45"
			required value="${empresa.nome}" /></td>
	</tr>
	<tr>
		<td><label for="email">E-mail</label></td>
		<td><input type="text" id="email" name="email" size="45" required
			value="${empresa.email}" /></td>
	</tr>


	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="text" id="senha" name="senha" size="5" required
			min="1" value="${empresa.senha}" /></td>
	</tr>
	<tr>
		<td><label for="cidade">Cidade</label></td>
		<td><input type="text" id="cidade" name="cidade" required
			min="500" step="any" size="5" value="${empresa.cidade}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>