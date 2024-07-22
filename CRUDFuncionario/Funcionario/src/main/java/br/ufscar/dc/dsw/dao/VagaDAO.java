package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
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
        ;
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
