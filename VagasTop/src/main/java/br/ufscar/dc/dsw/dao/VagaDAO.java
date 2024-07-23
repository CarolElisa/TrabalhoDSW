package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

public class VagaDAO extends GenericDAO {

    public void insert(Vaga vaga) {
        
        String sql = "INSERT INTO Vaga (funcao, nivel, anosContrato, salario, empresa_cnpj) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, vaga.getFuncao());
            statement.setString(2, vaga.getNivel());
            statement.setInt(3, vaga.getAnosContrato());
            statement.setFloat(4, vaga.getSalario());
            statement.setString(5, vaga.getEmpresaCnpj());

            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> getAll() {

        List<Vaga> listaVagas= new ArrayList<>();

        String sql = "SELECT usu.id as usuario, e.*, v.* FROM VAGA V\n" + 
        "JOIN EMPRESA E\n" + 
        "ON E.CNPJ = V.EMPRESA_CNPJ\n" + 
        "JOIN USUARIO USU\n" + 
        "ON USU.DOCUMENTO = E.CNPJ\n";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("usuario");
                String titulo = resultSet.getString("funcao");
                String autor = resultSet.getString("nivel");
                int ano = resultSet.getInt("anosContrato");
                float preco = resultSet.getFloat("salario");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                Empresa empresa = new Empresa(id, cnpj, nome, descricao, email, senha, cidade);
                Vaga vaga = new Vaga(id, titulo, autor, ano, preco, empresa);
                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

    public List<Candidatura> getCandidaturaVagaEspecifica(Long idVaga)
    {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        
        String sql = "SELECT U.ID AS ID_EMPRESA, e.cnpj, v.id as id_vaga, v.funcao, v.nivel, v.anosContrato, v.salario,";
        sql =  sql + "e.nome as nome_empr, ";
       sql =  sql + " e.descricao as desc_empr, ";
       sql =  sql + " e.email as email_empr, ";
       sql =  sql + " e.senha as senha_empr, ";
       sql =  sql + " e.cidade,";
       sql =  sql + " C.id as id_cand,";
       sql =  sql + " C.cpf as cand_cpf, ";
       sql =  sql + " case when c.status is null then " + "'ABERTO'" + " else C.status end as cand_status,";
       sql =  sql + " P.CPF as pro_cpf,";
       sql =  sql + " P.NOME as pro_nome,";
       sql =  sql + " P.EMAIL as pro_email,";
       sql =  sql + " P.SENHA as pro_senha,";
       sql =  sql + " P.TELEFONE as pro_telefone,";
       sql =  sql + " P.SEXO as pro_sexo,";
       sql =  sql + " P.DATANASC as pro_datanasc";
sql =  sql + " FROM CANDIDATURA C ";
		sql =  sql + " JOIN PROFISSIONAL P";
		sql =  sql + " 	ON P.CPF = C.CPF";
		sql =  sql + " JOIN VAGA V";
		sql =  sql + " 	ON V.ID = C.ID_VAGA";
		sql =  sql + " JOIN EMPRESA E ";
		sql =  sql + " 	ON E.CNPJ = V.EMPRESA_CNPJ ";
        sql = sql + "  JOIN USUARIO U ON U.DOCUMENTO = E.CNPJ";
sql =  sql + " WHERE ID_VAGA = " + idVaga;

        try {
            
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                Long id_cand = resultSet.getLong("id_cand");
                Long id_empr = resultSet.getLong("id_empresa");

                
                String cand_status = resultSet.getString("cand_status");

                String cnpj = resultSet.getString("cnpj");
                String nome_empr = resultSet.getString("nome_empr");
                String desc_empr = resultSet.getString("desc_empr");
                String email_empr = resultSet.getString("email_empr");
                String senha_empr = resultSet.getString("senha_empr");
                String cidade = resultSet.getString("cidade");

                String cpf = resultSet.getString("pro_cpf");
                Long id_vaga = resultSet.getLong("id_vaga");

                String funcao = resultSet.getString("funcao");
                String nivel = resultSet.getString("nivel");
                Integer anosContrato = resultSet.getInt("anosContrato");
                Float salario = resultSet.getFloat("salario");

                Profissional profissional = new Profissional(cpf);
                Empresa empresa = new Empresa(id_empr, cnpj, nome_empr, desc_empr, email_empr, senha_empr, cidade);
                
                

                Vaga vaga = new Vaga(id_vaga, funcao, nivel, anosContrato, salario, empresa);
                Candidatura candidatura = new Candidatura(id_cand, vaga, profissional, cand_status);

                listaCandidaturas.add(candidatura);
            }
            
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return listaCandidaturas;
    }

    public List<Vaga> getPorEmpresa(String CNPJ)
    {
        List<Vaga> listaVagas= new ArrayList<>();
        
        String sql = "SELECT usu.id as usuario, e.nome as nomeEmpr, e.*, v.* FROM VAGA V\n" + 
                        "JOIN EMPRESA E\n" + 
                        "ON E.CNPJ = V.EMPRESA_CNPJ\n" + 
                        "JOIN USUARIO USU\n" + 
                        "ON USU.DOCUMENTO = E.CNPJ\n" + 
                        "WHERE E.CNPJ = '" + CNPJ + "'";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titulo = resultSet.getString("funcao");
                String autor = resultSet.getString("nivel");
                int ano = resultSet.getInt("anosContrato");
                float preco = resultSet.getFloat("salario");
                String cnpj = resultSet.getString("cnpj");
                String emprNome = resultSet.getString("nomeEmpr");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                Empresa empresa = new Empresa(id, cnpj, nome, descricao, email, senha, cidade);
                Vaga vaga = new Vaga(id, titulo, autor, ano, preco, empresa);
                listaVagas.add(vaga);
            }
            
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVagas;
    }

    public void delete(Vaga vaga) {
        String sql = "DELETE FROM Vaga where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, vaga.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vaga vaga) {
        String sql = "UPDATE Vaga SET funcao = ?, nivel = ?, anosContrato = ?, salario = ?";
        sql += ", empresa_cnpj = ? WHERE id = ?";
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
           
            statement.setString(1, vaga.getFuncao());
            
            statement.setString(2, vaga.getNivel());
            
            statement.setInt(3, vaga.getAnosContrato());
            
            statement.setFloat(4, vaga.getSalario());
            
            statement.setString(5, vaga.getEmpresaCnpj());
            
            statement.setLong(6, vaga.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aprovaRecusa(Vaga vaga, String aprovado, String cpf)
    {
        String sql = "UPDATE Candidatura SET status = ?";
        sql += "WHERE id_vaga = ? and cpf = ?";
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
           
            statement.setString(1, aprovado);
            
            statement.setLong(2, vaga.getId());
            
            statement.setString(3, cpf);
            
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga get(Long id) {
        Vaga vaga = null;
        
        String sql = "SELECT * from Vaga v, Empresa e where v.id = ? and v.EMPRESA_CNPJ = e.Cnpj";

        try {

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String titulo = resultSet.getString("funcao");
                String autor = resultSet.getString("nivel");
                int ano = resultSet.getInt("anosContrato");
                float preco = resultSet.getFloat("salario");

                String empresaCNPJ = resultSet.getString("empresa_cnpj");
                Empresa empresa = new EmpresaDAO().get(empresaCNPJ);
                vaga = new Vaga(id, titulo, autor, ano, preco, empresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vaga;
    }
}
