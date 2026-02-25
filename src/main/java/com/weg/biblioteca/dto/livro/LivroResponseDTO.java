package com.weg.biblioteca.dto.livro;

public record LivroResponseDTO(
        Long idLivro,
        String titulo,
        String autor,
        int anoPublicacao
) {
}
