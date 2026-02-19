package com.weg.biblioteca.controller;

import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.service.LivroServiceImpl;
import com.weg.biblioteca.service.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroServiceImpl livroService;

    public LivroController(LivroServiceImpl livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public Livro cadastrarLivro(@RequestBody Livro livro){
        try{
            return livroService.salvarLivro(livro);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> listarLivros(){
        try{
            return livroService.listarLivro();
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Livro buscarLivroPorId(@PathVariable long id){
        try{
            return livroService.buscarLivroPorId(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarlivro(@PathVariable long id){
        try{
            livroService.deletarLivro(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void atualizarLivro(@PathVariable long id,
                               @RequestBody Livro livro){
        try{
            livroService.atualizarLivro(id,livro);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException();
        }
    }
}
