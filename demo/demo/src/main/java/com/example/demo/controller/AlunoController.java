package com.example.demo.controller;

import com.example.demo.dao.AlunoDao;
import com.example.demo.model.Aluno;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoDao dao = new AlunoDao();

    @Operation(summary = "Listar todos os alunos")
    @GetMapping
    public List<Aluno> listar() {
        return dao.listar();
    }

    @Operation(summary = "Buscar aluno por matrícula")
    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> buscar(@PathVariable String matricula) {
        Aluno aluno = dao.buscarPorMatricula(matricula);
        return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cadastrar aluno")
    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno) {
        dao.salvar(aluno);
        return aluno;
    }

    @Operation(summary = "Atualizar aluno")
    @PutMapping("/{matricula}")
    public Aluno atualizar(@PathVariable String matricula, @RequestBody Aluno aluno) {
        aluno.setMatricula(matricula);
        dao.atualizar(aluno);
        return aluno;
    }

    @Operation(summary = "Deletar aluno")
    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletar(@PathVariable String matricula) {
        dao.deletar(matricula);
        return ResponseEntity.noContent().build();
    }
}