package com.weg.biblioteca.controller;

import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.service.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario user){
        try{
            return usuarioService.salvarUsuario(user);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
