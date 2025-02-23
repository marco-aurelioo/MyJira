package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.Project;
import com.tiozao.tasks.aplication.converter.ProjectConverter;
import com.tiozao.tasks.services.PrivateProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CrudProjectController {

    @Autowired
    private ProjectConverter converter;
    
    @Autowired
    private PrivateProjectService service;
    
    
    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(
            @RequestBody Project project){
        return ResponseEntity.ok(
                converter.convertToDto(
                service.createProject(converter.convertToEntity(project))));
    }

    @GetMapping("/projects/{idProject}")
    public ResponseEntity<Project> findBprojectById(
            @PathVariable UUID idProject){
        return ResponseEntity.ok(
                converter.convertToDto(service.findProjectByExternalId(idProject.toString())));
    }

    @GetMapping("/projects/{unicName}")
    public ResponseEntity<Project> findBprojectById(
            @PathVariable String unicName){
        return ResponseEntity.ok( converter.convertToDto(service.findProjectByUnicName(unicName)));
    }

}
