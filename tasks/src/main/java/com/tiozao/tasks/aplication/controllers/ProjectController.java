package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.ProjectDto;
import com.tiozao.tasks.assembler.ProjectConverter;
import com.tiozao.tasks.domain.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

@Autowired
private ProjectService service;

@Autowired
private ProjectConverter converter;

    @GetMapping("/projects/{projectAlias}")
    public ResponseEntity<ProjectDto> findProject(
            @PathVariable("projectAlias") String projectAlias) {
        return ResponseEntity.ok(converter.convertDomain(service.find(projectAlias)));
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDto> createProject(
            @RequestBody ProjectDto project) {
        return ResponseEntity.ok(converter.convertDomain(service.create(converter.convertOrigin(project))));
    }

    @DeleteMapping("/projects/{projectAlias}")
    public ResponseEntity<Boolean> deleteProject(
            @PathVariable("projectAlias") String projectAlias) {
        return ResponseEntity.ok(service.delete(projectAlias));
    }

}
