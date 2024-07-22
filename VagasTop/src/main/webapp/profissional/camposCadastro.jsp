<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
	<caption>
		Cadastro
	</caption>
	
	<tr>
		<td><label for="cpf">CPF</label></td>
		<td><input type="text" id="cpf" name="cpf" size="45"
			required value="${profissional.cpf}" /></td>
	</tr>
	
	<tr>
		<td><label for="nome">Nome</label></td>
		<td><input type="text" id="nome" name="nome" size="45"
			required value="${profissional.nome}" /></td>
	</tr>

	<tr>
		<td><label for="email">E-mail</label></td>
		<td><input type="text" id="email" name="email" size="45"
			required value="${profissional.email}" /></td>
	</tr>

	<tr>
		<td><label for="senha">Senha</label></td>
		<td><input type="text" id="senha" name="senha" size="45"
			required value="${profissional.senha}" /></td>
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