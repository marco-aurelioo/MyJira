package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.ProjectRequest;
import com.tiozao.tasks.aplication.controllers.response.ProjectResponse;
import com.tiozao.tasks.assembler.ProjectConverter;
import com.tiozao.tasks.assembler.models.ProjectIn;
import com.tiozao.tasks.assembler.models.ProjectOut;
import com.tiozao.tasks.domain.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ProjetoController {

    @Autowired
    private ProjectService projetoService;

    @Autowired
    private ProjectConverter projectConverter;

    @PostMapping("/projeto/{organizations}")
    public ResponseEntity<ProjectResponse> createProject(
            @PathVariable(name="organizations") String organizations,
            @RequestBody ProjectRequest project,
            Principal principal){

        return ResponseEntity.ok(
                        new ProjectOut(
                projetoService.create(
                        projectConverter.convertOrigin(
                                new ProjectIn(organizations, project, principal))
                                .getProjectEntity())).getProjectResponse());
    }

}
