package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public void insert(Usuario usuario) {
        
        String sql = "INSERT INTO Usuario (nome, login, senha, papel, documento) VALUES (?, ?, ?, ?, ?)";


        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel());
            statement.setString(5, usuario.getDocumento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * from Usuario";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String documento = resultSet.getString("documento");
                Usuario usuario = new Usuario(id, nome, login, senha, papel, documento);
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(Usuario usuario) {

        String sql = "";
        System.out.println(usuario.getDocumento());
        if(usuario.getPapel().equals("EMPR"))
        {
            sql = "DELETE FROM EMPRESA WHERE CNPJ = '" + usuario.getDocumento() + "'"; 
        }
        else if(usuario.getPapel().equals("PROF"))
        {
            sql = "DELETE FROM PROFISSIONAL WHERE CPF = '" + usuario.getDocumento() + "'"; 
        }


        System.out.println(sql);
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        sql = "DELETE FROM Usuario where id = ?";
        try {
            Connection conn = this.getConnection();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, usuario.getId());
            
            
            statement.executeUpdate();
            System.out.println(sql + " " + usuario.getId());
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, login = ?, senha = ?, papel = ?, documento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel());
            statement.setString(5, usuario.getDocumento());
            statement.setLong(6, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get(Long id) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String documento = resultSet.getString("documento");

                usuario = new Usuario(id, nome, login, senha, papel, documento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return usuario;
    }
    




    public Usuario getbyLogin(String login) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE login = ?";
        //dados usuario geral
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
                
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String documento = resultSet.getString("documento");
                if(documento == null)
                {
                    documento = "admin";
                }
                usuario = new Usuario(id, nome, login, senha, papel, documento);
            }

            resultSet.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
}