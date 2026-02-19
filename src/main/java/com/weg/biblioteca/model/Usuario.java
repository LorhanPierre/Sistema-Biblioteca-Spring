package com.weg.biblioteca.model;

public class Usuario {

    private long idUsuario;
    private String nomeUsuario;
    private String email;

    public Usuario(String nomeUsuario, String email) {
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }

    public Usuario(long idUsuario, String nomeUsuario, String email) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }

    public Usuario(){
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void update(String email, String nome){
        if(!email.isBlank()){
            this.email = email;
        }
        if(!nome.isBlank()){
            this.nomeUsuario = nome;
        }
    }
}
