package com.weg.biblioteca.service;

import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.repository.LivroRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroServiceImpl {

    private final LivroRepositoryImpl livroRepositoryImpl;

    public LivroServiceImpl(LivroRepositoryImpl livroRepositoryImpl){
        this.livroRepositoryImpl = livroRepositoryImpl;
    }

    public Livro salvarLivro(Livro livro) throws SQLException {
        return livroRepositoryImpl.salvarLivro(livro);
    }

    public List<Livro> listarLivro() throws SQLException{
        return livroRepositoryImpl.listarLivros();
    }

    public void deletarLivro(long id) throws SQLException{
        livroRepositoryImpl.deletarLivro(id);
    }

    public Livro buscarLivroPorId(long id) throws SQLException{
        return livroRepositoryImpl.buscarLivroPorId(id);
    }

    public void atualizarLivro(long id,Livro livro) throws SQLException{
        Livro book= livroRepositoryImpl.buscarLivroPorId(id);

        book.update(livro.getTitulo(),livro.getAutor(), livro.getAnoPublicacao());

        livroRepositoryImpl.atualizarLivro(id,book);
    }
}
