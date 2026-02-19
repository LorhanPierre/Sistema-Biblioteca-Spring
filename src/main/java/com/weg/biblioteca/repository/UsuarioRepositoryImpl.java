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
}
