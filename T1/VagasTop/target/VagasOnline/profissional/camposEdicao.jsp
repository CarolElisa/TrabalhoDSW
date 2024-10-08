<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="message">
<table border="1">
	<caption>
		<c:choose>
			<c:when test="${livro != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	
	<c:if test="${profissional != null}">
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
				<option value="PROF" ${usuario.papel == "PROF" ? 'selected="selected"' : ''}>PROF</option>
			</select>			
		</td>
	</tr>
	
	<tr>
		<td><label for="telefone">Telefone</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45"
			required value="${profissional.telefone}" /></td>
	</tr>
	<tr>
		<td><label for="sexo">Sexo</label></td>
		<td><input type="text" id="sexo" name="sexo" size="45"
			required value="${profissional.sexo}" /></td>
	</tr>
	<tr>
		<td><label for="datanasc">Data de Nascimento</label></td>
		<td><input type="text" id="datanasc" name="datanasc" size="45"
			required value="${profissional.datanasc}" /></td>
	</tr>

	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>
</fmt:bundle>