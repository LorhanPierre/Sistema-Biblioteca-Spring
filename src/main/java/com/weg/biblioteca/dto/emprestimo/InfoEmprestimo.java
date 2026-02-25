package com.weg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record InfoEmprestimo(
        long idEmprestimo,
        String tituloLivro,
        String nomeUsuario,
        LocalDate data_emprestimo,
        LocalDate data_devolucao) {
}
