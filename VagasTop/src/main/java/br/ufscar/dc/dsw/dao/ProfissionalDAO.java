package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;


public class ProfissionalDAO extends GenericDAO {

    public void insert(Profissional profissional) {

        String sql = "INSERT INTO Profissional (cpf, nome, email, senha, telefone, sexo, datanasc) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.setString(2, profissional.getNome());
            statement.setString(3, profissional.getEmail());
            statement.setString(4, profissional.getSenha());
            statement.setString(5, profissional.getTelefone());
            statement.setString(6, profissional.getSexo());
            statement.setString(7, profissional.getDatanasc());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Profissional> getAll() {

        List<Profissional> listaProfissionais = new ArrayList<>();

        String sql = "SELECT * from Profissional order by nome";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String datanasc = resultSet.getString("datanasc");
                
                Profissional profissional = new Profissional(cpf, nome, email, senha, telefone, sexo, datanasc);
    
                listaProfissionais.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissionais;
    }

    public void delete(Profissional profissional) {
        String sql = "DELETE FROM Profissional where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Profissional profissional) {
        String sql = "UPDATE Profissional SET nome = ?, email = ?, senha = ?, telefone = ?, sexo = ?, datanasc = ?,  WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, profissional.getNome());
            statement.setString(2, profissional.getEmail());
            statement.setString(3, profissional.getSenha());
            statement.setString(4, profissional.getTelefone());
            statement.setString(5, profissional.getSexo());
            statement.setString(6, profissional.getDatanasc());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Profissional get(String cpf) {
        Profissional profissional = null;

        String sql = "SELECT * from Profissional where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String datanasc = resultSet.getString("datanasc");

                profissional = new Profissional(cpf, nome, email, senha, telefone, sexo, datanasc);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }
}
