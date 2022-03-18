package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.TaskDto;
import com.tiozao.tasks.assembler.TaskDetailConverter;
import com.tiozao.tasks.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskDetailConverter converter;

    @GetMapping("/project/{alias}/tasks")
    public ResponseEntity<Page<TaskDto>> getAllTasksProject(
            @PathVariable("alias") String alias,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "0") Integer size){
        return ResponseEntity.ok(converter.createPageFromEntities(taskService.findAllTasks(alias, PageRequest.of(page,size))));
    }




}
