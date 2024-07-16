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

    public List<Profissional> getAll() {

        List<Profissional> listaProfissional = new ArrayList<>();

        String sql = "SELECT * from Profissional";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataDeNascimento = resultSet.getString("dataDeNascimento");
                Profissional profissional = new Profissional(cpf, telefone, sexo, dataDeNascimento);
                listaProfissional.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissional;
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
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String dataDeNascimento = resultSet.getString("dataDeNascimento");
                profissional = new Profissional(cpf, telefone, sexo, dataDeNascimento);
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
