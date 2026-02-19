package com.weg.biblioteca.controller;

import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.service.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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

    @GetMapping
    public List<Usuario> listarUsuarios(){
        try{
            return usuarioService.listarUsuario();
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable long id){
        try{
            return usuarioService.buscarUsuarioPorId(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable long id){
        try{
            usuarioService.deletarUsuario(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable long id,
                                 @RequestBody Usuario user){
        try{
            usuarioService.atualizarUsuario(id,user);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException();
        }
    }
}
