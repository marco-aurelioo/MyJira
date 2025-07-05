package com.tiozao.tasks.aplication.controllers;


import com.tiozao.tasks.aplication.controllers.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    //CRUD -- tem que ser membro do projeto
    ///project/{sigla}/tasks - GET, POST (tem que ter permissao criador tarefas)

    @GetMapping("/project/{sigla_projeto}/tasks")
    public ResponseEntity<Page<String>> buscaTarefas(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size){
        return ResponseEntity.ok(Page.empty());
    }

    @PostMapping("/project/{sigla_projeto}/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(task);
    }


    ///project/{sigla}/tasks/{id-externo} GET, PUT , DELETE, PATCH
    @GetMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<String> buscaTarefa(
           @PathVariable("sigla_projeto") String siglaProjeto,
           @PathVariable("task_id") String taskId ){
        return ResponseEntity.ok("sucesso");
    }

    @PutMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<Task> atualizaTarefa(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @PathVariable("task_id") String taskId ,
            @RequestBody Task task){
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<Task> atualizaParcialTarefa(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @PathVariable("task_id") String taskId ,
            @RequestBody Task body){
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<Boolean> deletaTarefa(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @PathVariable("task_id") String taskId ){
        return ResponseEntity.ok(true);
    }

}
