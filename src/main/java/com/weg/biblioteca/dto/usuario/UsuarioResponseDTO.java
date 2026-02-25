package com.weg.biblioteca.dto.usuario;

public record UsuarioResponseDTO(
        Long idUsuario,
        String nome,
        String email
) {
}
