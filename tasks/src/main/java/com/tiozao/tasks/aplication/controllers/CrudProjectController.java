package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.Project;
import com.tiozao.tasks.aplication.converter.ProjectConverter;
import com.tiozao.tasks.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CrudProjectController {

    @Autowired
    private ProjectConverter converter;
    
    @Autowired
    private ProjectService service;


    
    
    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(
            @RequestBody Project project){
        return ResponseEntity.ok(
                converter.convertToDto(
                service.createProject(converter.convertToEntity(project))));
    }

    @GetMapping("/projects/{value}")
    public ResponseEntity<Project> findBprojectById(
            @PathVariable("value") String value){
        if (value.matches("^[0-9a-fA-F-]{36}$")) { // Regex para UUID
            return ResponseEntity.ok(
                    converter.convertToDto(service.findProjectByExternalId(value)));
        } else {
            return ResponseEntity.ok(
                    converter.convertToDto(service.findProjectByUnicName(value)));
        }
    }

    @GetMapping("/projects/")
    public ResponseEntity<Page<Project>> findBprojectById(
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size){
        return ResponseEntity.ok(
                converter.convertPageToDto(
                        service.findProjectByOwner(
                                PageRequest.of( page, size,
                                        Sort.by("unicName")))));
    }


    @GetMapping("/projects/member/")
    public ResponseEntity<Page<Project>> findBprojectByImMember(
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size){
        return ResponseEntity.ok(
                converter.convertPageToDto(
                        service.findProjectImMember(
                                PageRequest.of( page, size,
                                        Sort.by("id")))));
    }

}
