package com.weg.biblioteca.service;

import com.weg.biblioteca.dto.emprestimo.EmprestimoRequestDTO;
import com.weg.biblioteca.dto.emprestimo.EmprestimoResponseDTO;
import com.weg.biblioteca.dto.emprestimo.InfoEmprestimo;
import com.weg.biblioteca.mapper.emprestimo.EmprestimoMapper;
import com.weg.biblioteca.model.Emprestimo;
import com.weg.biblioteca.repository.EmprestimoRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoServiceImpl {

    private final EmprestimoRepositoryImpl emprestimoRepository;

    private final EmprestimoMapper emprestimoMapper;

    public EmprestimoServiceImpl(
            EmprestimoRepositoryImpl emprestimoRepository,
            EmprestimoMapper emprestimoMapper
    ){
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    public EmprestimoResponseDTO salvarEmprestimo(EmprestimoRequestDTO novoEmprestimo) throws SQLException {
        Emprestimo emprestimo = emprestimoMapper.toEntity(novoEmprestimo);

        emprestimoRepository.salvarEmprestimo(emprestimo);

        return emprestimoMapper.toResponseDTO(emprestimo);
    }

    public List<InfoEmprestimo> listarEmprestimo() throws SQLException{
        return emprestimoRepository.listarEmprestimos();
    }

    public InfoEmprestimo mostrarEmprestimo(Long id) throws SQLException{
        return emprestimoRepository.mostrarEmprestimosPorId(id);
    }

    public void excluirEmprestimo(long id) throws SQLException{
        emprestimoRepository.excluirEmprestimo(id);
    }

    public void atualizarEmprestimo(Long id,EmprestimoRequestDTO emprestimoRequest) throws SQLException {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(id);

        emprestimo.update(
                emprestimoRequest.idLivro(),
                emprestimoRequest.idUsuario(),
                emprestimoRequest.dataEmprestimo(),
                emprestimoRequest.dataDevolucao()
        );

        emprestimoRepository.atualizarEmprestimo(id,emprestimo);
    }

}
