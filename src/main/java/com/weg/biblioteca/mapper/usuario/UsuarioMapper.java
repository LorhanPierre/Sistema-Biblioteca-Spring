package com.weg.biblioteca.mapper.usuario;

import com.weg.biblioteca.dto.usuario.UsuarioRequestDTO;
import com.weg.biblioteca.dto.usuario.UsuarioResponseDTO;
import com.weg.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO){
        return new Usuario(
                usuarioRequestDTO.nome(),
                usuarioRequestDTO.email()
        );
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNomeUsuario(),
                usuario.getEmail()
        );
    }
}
