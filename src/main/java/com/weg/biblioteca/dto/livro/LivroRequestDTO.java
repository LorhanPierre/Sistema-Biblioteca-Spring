package com.weg.biblioteca.dto.livro;

public record LivroRequestDTO(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
