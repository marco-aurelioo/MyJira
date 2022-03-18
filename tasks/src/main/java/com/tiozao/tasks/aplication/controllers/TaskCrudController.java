package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.TaskCreateDto;
import com.tiozao.tasks.assembler.TaskCreateConverter;
import com.tiozao.tasks.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskCrudController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskCreateConverter taskCreateConverter;


    @PostMapping("/project/{alias}/tasks")
    public ResponseEntity<TaskCreateDto> createTask(
            @PathVariable("alias") String alias,
            @RequestBody TaskCreateDto task){
        return ResponseEntity.ok(taskCreateConverter.convertDomain(
                taskService.createTask(alias,
                        taskCreateConverter.convertOrigin(task))
        ));
    }
}
