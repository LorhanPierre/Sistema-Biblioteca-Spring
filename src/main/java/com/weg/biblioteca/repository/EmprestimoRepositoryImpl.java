package com.weg.biblioteca.repository;

import com.weg.biblioteca.database.Conexao;
import com.weg.biblioteca.dto.emprestimo.InfoEmprestimo;
import com.weg.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.LocaleResolver;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepositoryImpl{

    private final LocaleResolver localeResolver;

    public EmprestimoRepositoryImpl(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo) throws SQLException {

        String query = """
                INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao)VALUES (?,?,?,?);
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1,emprestimo.getIdLivro());
            stmt.setLong(2,emprestimo.getIdUsuario());
            stmt.setObject(3,emprestimo.getDataEmprestimo());
            stmt.setObject(4,emprestimo.getDataDevolucao());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                emprestimo.setIdLivro(rs.getLong(1));
                return emprestimo;
            }
        }
        throw new RuntimeException("Erro ao salvar o empréstimo");
    }

    public List<InfoEmprestimo> listarEmprestimos() throws SQLException{
        List<InfoEmprestimo> emprestimos = new ArrayList<>();

        String query = """
                SELECT
                e.id,
                l.titulo,
                u.nome,
                e.data_emprestimo,
                e.data_devolucao
                FROM emprestimo e
                INNER JOIN usuario u
                ON e.usuario_id = u.id
                INNER JOIN livro l
                ON e.livro_id = l.id;
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("nome");
                LocalDate dataEmprestimo = rs.getObject("data_emprestimo",LocalDate.class);
                LocalDate dataDevolucao = rs.getObject("data_devolucao",LocalDate.class);
                emprestimos.add(new InfoEmprestimo(id,titulo,autor,dataEmprestimo,dataDevolucao));
            }
        }

        return emprestimos;
    }

    public InfoEmprestimo mostrarEmprestimosPorId(long idEmprestimo) throws SQLException{

        String query = """
                SELECT
                e.id,
                l.titulo,
                u.nome,
                e.data_emprestimo,
                e.data_devolucao
                FROM emprestimo e
                INNER JOIN usuario u
                ON e.usuario_id = u.id
                INNER JOIN livro l
                ON e.livro_id = l.id;
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            if(rs.next()){
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("nome");
                LocalDate dataEmprestimo = rs.getObject("data_emprestimo",LocalDate.class);
                LocalDate dataDevolucao = rs.getObject("data_devolucao",LocalDate.class);
                return new InfoEmprestimo(id,titulo,autor,dataEmprestimo,dataDevolucao);
            }
        }

        throw new RuntimeException("Emprestimo não encontrado");
    }
    public void excluirEmprestimo(long id) throws SQLException{

        String query = """
                DELETE FROM emprestimo WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1,id);
            stmt.execute();
        }
    }

    public void atualizarEmprestimo(long id,Emprestimo emprestimo) throws SQLException{

        String query = """
                UPDATE emprestimo SET livro_id = ? , usuario_id = ?, data_emprestimo = ?, data_devolucao = ?  WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1,emprestimo.getIdLivro());
            stmt.setLong(2,emprestimo.getIdUsuario());
            stmt.setObject(3,emprestimo.getDataEmprestimo());
            stmt.setObject(4,emprestimo.getDataDevolucao());
            stmt.setLong(5,id);
            stmt.execute();
        }
    }

    public Emprestimo buscarPorId(Long idEmprestimo) throws SQLException{

        String query = """
                SELECT
                    id,
                    livro_id,
                    usuario_id,
                    data_emprestimo,
                    data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1,idEmprestimo);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            if(rs.next()){
                long id = rs.getLong("id");
                long idLivro = rs.getLong("livro_id");
                long idUsuario = rs.getLong("usuario_id");
                LocalDate dataEmprestimo = rs.getObject("data_emprestimo",LocalDate.class);
                LocalDate dataDevolucao = rs.getObject("data_devolucao",LocalDate.class);
                return new Emprestimo(id,idLivro,idUsuario,dataEmprestimo,dataDevolucao);
            }
        }
        return null;
    }
}
