package com.weg.biblioteca.controller;

import com.weg.biblioteca.dto.emprestimo.EmprestimoRequestDTO;
import com.weg.biblioteca.dto.emprestimo.EmprestimoResponseDTO;
import com.weg.biblioteca.dto.emprestimo.InfoEmprestimo;
import com.weg.biblioteca.service.EmprestimoServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoServiceImpl emprestimoService;

    public EmprestimoController (EmprestimoServiceImpl emprestimoService){
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public EmprestimoResponseDTO salvarEmprestimo(
            @RequestBody EmprestimoRequestDTO novoEmprestimo) throws SQLException
    {
        try{
            return emprestimoService.salvarEmprestimo(novoEmprestimo);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<InfoEmprestimo> listarEmprestimos(){
        try{
            return emprestimoService.listarEmprestimo();
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public InfoEmprestimo mostrarEmprestimo(
            @PathVariable Long id
    ){
        try{
            return emprestimoService.mostrarEmprestimo(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void excluir(
            @PathVariable Long id
    ){
        try{
            emprestimoService.excluirEmprestimo(id);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void atualizarEmprestimo(
            @PathVariable Long id,
            @RequestBody EmprestimoRequestDTO emprestimo
    ){
        try{
            emprestimoService.atualizarEmprestimo(id, emprestimo);
        }catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
