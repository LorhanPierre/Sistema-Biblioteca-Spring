package com.weg.biblioteca.mapper.livro;

import com.weg.biblioteca.dto.livro.LivroRequestDTO;
import com.weg.biblioteca.dto.livro.LivroResponseDTO;
import com.weg.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequestDTO livroRequestDTO){
        return new Livro(
                livroRequestDTO.titulo(),
                livroRequestDTO.autor(),
                livroRequestDTO.anoPublicacao()
        );
    }

    public LivroResponseDTO toResponseDTO(Livro livro){
        return new LivroResponseDTO(
                livro.getIdLivro(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAnoPublicacao()
        );
    }
}
