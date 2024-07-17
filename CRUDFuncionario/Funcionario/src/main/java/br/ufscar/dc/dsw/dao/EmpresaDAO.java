package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaDAO extends GenericDAO {

    public List<Empresa> getAll() {
        List<Empresa> listaEmpresas = new ArrayList<>();
        String sql = "SELECT * from Empresa order by nome";
    
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
    
            while (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                Empresa empresa = new Empresa(cnpj, nome, descricao, email, senha, cidade);
                listaEmpresas.add(empresa);
            }
    
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEmpresas;
    }
    

    public Empresa get(String x) {
        Empresa empresa = null;
        
        String sql = "SELECT * from Empresa where cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, x);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                empresa = new Empresa(cnpj, nome, descricao, email, senha, cidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empresa;
    }

    public void insert(Empresa empresa) {

        String sql = "INSERT INTO Empresa (cnpj, nome, descricao, email, senha, cidade) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getNome());
            statement.setString(3, empresa.getDescricao());
            statement.setString(4, empresa.getEmail());
            statement.setString(5, empresa.getSenha());
            statement.setString(6, empresa.getCidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Empresa empresa) {
        String sql = "UPDATE Empresa SET cnpj = ?, nome = ?, descricao = ?";
        sql += ", email = ?, senha = ?, cidade = ? WHERE cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.setString(2, empresa.getNome());
            statement.setString(3, empresa.getDescricao());
            statement.setString(4, empresa.getEmail());
            statement.setString(5, empresa.getSenha());
            statement.setString(6, empresa.getCidade());
            statement.setString(7, empresa.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(Empresa empresa) {
        String sql = "DELETE FROM Vaga where empresa_cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "DELETE FROM Empresa where cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, empresa.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
