package com.weg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
        long idEmprestimo,
        long idLivro,
        long idUsuario,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
