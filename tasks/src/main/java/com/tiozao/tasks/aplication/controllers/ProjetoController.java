package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.ProjectRequest;
import com.tiozao.tasks.aplication.controllers.response.ProjectResponse;
import com.tiozao.tasks.assembler.converters.ProjectConverter;
import com.tiozao.tasks.assembler.converters.models.ProjectIn;
import com.tiozao.tasks.assembler.converters.models.ProjectOut;
import com.tiozao.tasks.domain.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/projeto/{organizations}")
    public ResponseEntity<List<ProjectResponse>> createProject(
            @PathVariable(name="organizations") String organizations,
                 Principal principal){

        return ResponseEntity.ok(
                projetoService.findByOrganizations(
                        ((JwtAuthenticationToken) principal).getToken().getClaim("sub"),
                        organizations).stream().map(item -> {
                            return new ProjectResponse(
                                    item.getProjectName(),
                                    item.getDescription(),
                                    item.getProjectAlias());})
                        .collect(Collectors.toList()));
    }

}
