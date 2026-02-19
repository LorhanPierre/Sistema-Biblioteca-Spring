package com.weg.biblioteca.service;

import com.weg.biblioteca.model.Usuario;
import com.weg.biblioteca.repository.UsuarioRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioServiceImpl {

    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public UsuarioServiceImpl(UsuarioRepositoryImpl usuarioRepositoryImpl){
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
    }

    public Usuario salvarUsuario(Usuario user) throws SQLException {
        return usuarioRepositoryImpl.salvarUsuario(user);
    }

    public List<Usuario> listarUsuario() throws SQLException{
        return usuarioRepositoryImpl.listarUsuarios();
    }

    public void deletarUsuario(long id) throws SQLException{
        usuarioRepositoryImpl.deletarUsuario(id);
    }

    public Usuario buscarUsuarioPorId(long id) throws SQLException{
        return usuarioRepositoryImpl.buscarUsuarioPorId(id);
    }

    public void atualizarUsuario(long id,Usuario user) throws SQLException{
        Usuario usuario = usuarioRepositoryImpl.buscarUsuarioPorId(id);

        usuario.update(user.getEmail(),user.getNomeUsuario());

        usuarioRepositoryImpl.atualizarUsuario(id,usuario);
    }
}
