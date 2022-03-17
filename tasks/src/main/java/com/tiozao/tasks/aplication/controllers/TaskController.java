package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.TaskDto;
import com.tiozao.tasks.domain.entity.TaskEntity;
import com.tiozao.tasks.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/project/{alias}/tasks")
    public ResponseEntity<Page<TaskEntity>> getAllTasksProject(@PathVariable("alias") String alias){
        return ResponseEntity.ok(taskService.findAllTasks(alias));
    }
}
