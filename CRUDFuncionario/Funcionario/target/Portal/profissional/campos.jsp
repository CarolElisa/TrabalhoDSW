<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<input type="hidden" name="id" value="${profissional.cpf}" />
	</c:if>
	<tr>
		<td><label for="telefone">Telefone</label></td>
		<td><input type="text" id="telefone" name="telefone" size="45"
			required value="${profissional.telefone}" /></td>
	</tr>
	<tr>
		<td><label for="sexo">Sexo</label></td>
		<td><input type="text" id="sexo" name="sexo" size="45" required
			value="${profissional.sexo}" /></td>
	</tr>
	<tr>
		<td><label for="dataDeNascimento">Data de Nascimento</label></td>
		<td><input type="number" id="dataDeNascimento" name="dataDeNascimento" size="5" required
			min="1500" value="${profissional.dataDeNascimento}" /></td>
	</tr>
	<tr>
		<td><label for="cpf">CPF</label></td>
		<td><input type="number" id="cpf" name="cpf" required
			min="0.01" step="any" size="5" value="${profissional.cpf}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>