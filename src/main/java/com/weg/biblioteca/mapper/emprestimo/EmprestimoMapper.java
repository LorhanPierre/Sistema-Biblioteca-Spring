package com.weg.biblioteca.mapper.emprestimo;

import com.weg.biblioteca.dto.emprestimo.EmprestimoRequestDTO;
import com.weg.biblioteca.dto.emprestimo.EmprestimoResponseDTO;
import com.weg.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public Emprestimo toEntity(EmprestimoRequestDTO emprestimoRequest){
        return new Emprestimo(
                emprestimoRequest.idLivro(),
                emprestimoRequest.idUsuario(),
                emprestimoRequest.dataEmprestimo(),
                emprestimoRequest.dataDevolucao()
        );
    }

    public EmprestimoResponseDTO toResponseDTO(Emprestimo emprestimo){
        return new EmprestimoResponseDTO(
                emprestimo.getIdEmprestimo(),
                emprestimo.getIdLivro(),
                emprestimo.getIdUsuario(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }
}
