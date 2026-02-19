package com.weg.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {

    private long idEmprestimo;
    private long idLivro;
    private long idUsuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(long idEmprestimo, long idLivro, long idUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(long idLivro, long idUsuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo() {
    }

    public long getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(long idLivro) {
        this.idLivro = idLivro;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
