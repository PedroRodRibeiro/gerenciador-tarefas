package com.example.Atividade_Processual_2.controller;


import com.example.Atividade_Processual_2.repository.TarefaRepository;
import com.example.Atividade_Processual_2.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    // GET: Listar todas as tarefas
    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    // POST: Criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = repository.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    // PUT: Atualizar uma tarefa existente (Tratando caso de falha/sucesso)
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return repository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(tarefaAtualizada.getTitulo());
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    Tarefa atualizada = repository.save(tarefa);
                    return ResponseEntity.ok().body(atualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Excluir uma tarefa (Tratando caso de falha/sucesso)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarefa -> {
                    repository.delete(tarefa);
                    return ResponseEntity.noContent().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}