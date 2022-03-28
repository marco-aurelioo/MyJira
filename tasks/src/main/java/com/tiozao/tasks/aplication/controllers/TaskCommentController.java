package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.TaskCommentDto;
import com.tiozao.tasks.assembler.PersonConverter;
import com.tiozao.tasks.assembler.TaskCommentConverter;
import com.tiozao.tasks.domain.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaskCommentController {

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private TaskCommentConverter taskCommentConverter;

    @PostMapping("/tasks/{taskAlias}/comments")
    public ResponseEntity<TaskCommentDto> createTaskComment(
            @PathVariable("taskAlias") String taskAlias,
            @RequestBody TaskCommentDto task) {
        return ResponseEntity.ok(
                taskCommentConverter.convertDomain(
                        taskCommentService.createComment(
                                taskAlias,
                                taskCommentConverter.convertOrigin(task))));
    }

    @GetMapping("/tasks/{taskAlias}/comments")
    public ResponseEntity<Page<TaskCommentDto>> getComments(
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "20") int size,
            @PathVariable("taskAlias") String taskAlias) {
        return ResponseEntity.ok(taskCommentConverter.createPageFromEntities(
                taskCommentService.findComments(
                        taskAlias,
                        PageRequest.of(page, size))));
    }


}
