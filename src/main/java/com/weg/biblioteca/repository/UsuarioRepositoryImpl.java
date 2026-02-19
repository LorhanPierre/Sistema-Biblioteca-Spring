package com.weg.biblioteca.repository;

import com.weg.biblioteca.database.Conexao;
import com.weg.biblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl {

    public Usuario salvarUsuario(Usuario novoUsuario) throws SQLException {

        String query = """
                INSERT INTO usuario (nome,email)VALUES (?,?);
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1,novoUsuario.getNomeUsuario());
                stmt.setString(2,novoUsuario.getEmail());
                stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                novoUsuario.setIdUsuario(rs.getLong(1));
                return novoUsuario;
            }
        }
        throw new RuntimeException("Erro ao salvar o Usúario");
    }

    public List<Usuario> listarUsuarios() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();

        String query = """
                SELECT
                id,
                nome,
                email
                FROM usuario
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                usuarios.add(new Usuario(id,nome,email));
            }
        }

        return usuarios;
    }

    public Usuario buscarUsuarioPorId(long idUser) throws SQLException {

        String query = """
                SELECT
                id,
                nome,
                email
                FROM usuario
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, idUser);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                return new Usuario(id, nome, email);
            }
        }
        throw new RuntimeException("Usuario não encontrado");
    }

    public void deletarUsuario(long id) throws SQLException{

        String query = """
                DELETE FROM usuario WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1,id);
            stmt.execute();
        }
    }

    public void atualizarUsuario(long id,Usuario user) throws SQLException{

        String query = """
                UPDATE usuario SET nome = ?, email = ? WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,user.getNomeUsuario());
            stmt.setString(2,user.getEmail());
            stmt.setLong(3,id);
            stmt.execute();
        }
    }
}
