package com.weg.biblioteca.repository;

import com.weg.biblioteca.database.Conexao;
import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepositoryImpl {

    public Livro salvarLivro(Livro novoLivro) throws SQLException {

        String query = """
                INSERT INTO livro (titulo,autor,ano_publicacao)VALUES (?,?,?);
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, novoLivro.getTitulo());
            stmt.setString(2,novoLivro.getAutor());
            stmt.setInt(3,novoLivro.getAnoPublicacao());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                novoLivro.setIdLivro(rs.getLong(1));
                return novoLivro;
            }
        }
        throw new RuntimeException("Erro ao salvar o Livro");
    }

    public List<Livro> listarLivros() throws SQLException{
        List<Livro> livros = new ArrayList<>();

        String query = """
                SELECT
                id,
                titulo,
                autor,
                ano_publicacao
                FROM livro
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");

                livros.add(new Livro(id,titulo,autor,anoPublicacao));
            }
        }

        return livros;
    }

    public Livro buscarLivroPorId(long idBook) throws SQLException {

        String query = """
                SELECT
                id,
                titulo,
                autor,
                ano_publicacao
                FROM livro
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, idBook);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anoPublicacao = rs.getInt("ano_publicacao");

                return new Livro(id,titulo,autor,anoPublicacao);
            }
        }
        throw new RuntimeException("Livro não encontrado");
    }

    public void deletarLivro(long id) throws SQLException{

        String query = """
                DELETE FROM livro WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1,id);
            stmt.execute();
        }
    }

    public void atualizarLivro(long id,Livro livro) throws SQLException{

        String query = """
                UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2,livro.getAutor());
            stmt.setInt(3,livro.getAnoPublicacao());
            stmt.setLong(4,id);
            stmt.execute();
        }
    }
}
