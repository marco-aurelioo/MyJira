package com.tiozao.tasks.aplication.controllers;


import com.tiozao.tasks.aplication.controllers.model.TaskDto;
import com.tiozao.tasks.aplication.converter.TaksConverter;
import com.tiozao.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    //CRUD -- tem que ser membro do projeto
    ///project/{sigla}/tasks - GET, POST (tem que ter permissao criador tarefas)

    @Autowired
    private TaskService service;

    @Autowired
    private TaksConverter converter;

    @GetMapping("/project/{sigla_projeto}/tasks")
    public ResponseEntity<Page<TaskDto>> buscaTarefas(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size){
        return ResponseEntity.ok(converter.convertToPageDto(service.buscaTasks(siglaProjeto, PageRequest.of(page,size))));
    }

    @PostMapping("/project/{sigla_projeto}/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        return ResponseEntity.ok(converter.convertToDto(service.createTask(converter.convertToEntity(task))));
    }


    ///project/{sigla}/tasks/{id-externo} GET, PUT , DELETE, PATCH
    @GetMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<String> buscaTarefa(
           @PathVariable("sigla_projeto") String siglaProjeto,
           @PathVariable("task_id") String taskId ){
        return ResponseEntity.ok("sucesso");
    }

    @PutMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<TaskDto> atualizaTarefa(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @PathVariable("task_id") String taskId ,
            @RequestBody TaskDto task){
        return ResponseEntity.ok(
                converter.convertToDto(
                        service.atualizaTask(siglaProjeto,
                                converter.convertToEntity(taskId,task)))
        );
    }

    @PatchMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<TaskDto> atualizaParcialTarefa(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @PathVariable("task_id") String taskId ,
            @RequestBody TaskDto body){
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/project/{sigla_projeto}/tasks/{task_id}")
    public ResponseEntity<Boolean> deletaTarefa(
            @PathVariable("sigla_projeto") String siglaProjeto,
            @PathVariable("task_id") String taskId ){
        return ResponseEntity.ok(true);
    }

}
