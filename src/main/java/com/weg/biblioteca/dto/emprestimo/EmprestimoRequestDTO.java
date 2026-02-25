package com.weg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        long idLivro,
        long idUsuario,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
