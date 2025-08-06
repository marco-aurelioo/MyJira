package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.ProjectConfiguration;
import com.tiozao.tasks.services.ProjectConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectConfigurationController {

    @Autowired
    private ProjectConfigurationService service;

    @GetMapping("/project/{unic_name}/config")
    public ResponseEntity<ProjectConfiguration> getConfig(
            @PathVariable("unic_name") String unicName){
        return ResponseEntity.ok(new ProjectConfiguration());
    }

    @PutMapping("/project/{unic_name}/config")
    public ResponseEntity<ProjectConfiguration> updateConfig(
            @PathVariable("unic_name") String unicName){
        return ResponseEntity.ok(new ProjectConfiguration());
    }



}
