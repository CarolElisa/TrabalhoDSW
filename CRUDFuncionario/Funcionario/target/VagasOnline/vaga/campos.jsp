<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
	<caption>
		<c:choose>
			<c:when test="${vaga != null}">
                            Edição
                        </c:when>
			<c:otherwise>
                            Cadastro
                        </c:otherwise>
		</c:choose>
	</caption>
	<c:if test="${vaga != null}">
		<input type="hidden" name="id" value="${vaga.id}" />
	</c:if>
	<tr>
		<td><label for="funcao">Funcao</label></td>
		<td><input type="text" id="funcao" name="funcao" size="45"
			required value="${vaga.funcao}" /></td>
	</tr>
	<tr>
		<td><label for="nivel">Nivel</label></td>
		<td><input type="text" id="nivel" name="nivel" size="45" required
			value="${vaga.nivel}" /></td>
	</tr>
	<tr>
		<td><label for="empresa">Empresa</label></td>
		<td><select id="empresa" name="empresa">
				<c:forEach items="${empresas}" var="empresa">
					<option value="${empresa.key}"
						${vaga.empresa.nome==empresa.value ? 'selected' : '' }>
						${empresa.value}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td><label for="anosContrato">Anos Contrato</label></td>
		<td><input type="number" id="anosContrato" name="anosContrato" size="5" required
			min="1" value="${vaga.anosContrato}" /></td>
	</tr>
	<tr>
		<td><label for="salario">Salario</label></td>
		<td><input type="number" id="salario" name="salario" required
			min="500" step="any" size="5" value="${vaga.salario}" /></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Salva" /></td>
	</tr>
</table>