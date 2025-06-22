package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.Participar;
import com.tiozao.tasks.aplication.controllers.model.ProjectParticipation;
import com.tiozao.tasks.aplication.converter.ParticipationConverter;
import com.tiozao.tasks.aplication.converter.ProjectParticipationConverter;
import com.tiozao.tasks.services.ProjectAproveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PrivateProjectController {


    @Autowired
    private ProjectAproveRequestService service;

    @Autowired
    private ProjectParticipationConverter converter;

    @Autowired
    private ParticipationConverter participationConverter;

    @GetMapping("/participar/projects-approve/")
    public ResponseEntity<Page<ProjectParticipation>> buscaSolicitacoes(
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size,
            @RequestParam(name="termo", required = false,defaultValue = "") String termo
    ){

        return ResponseEntity.ok(
                converter.convertPage(service
                        .findRequestsToMyProjects(
                            PageRequest.of( page, size, Sort.by("owner_id")))));

    }

    @GetMapping("/participar/projects-approve/project/{projectId}")
    public ResponseEntity<Page<Participar>> buscaSolicitacoesPorProjeto(
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size,
            @PathVariable("projectId") String projectId

    ){

        return ResponseEntity.ok(
                participationConverter.convertPage(service
                        .findRequestsToMyProject(projectId, PageRequest.of( page, size, Sort.by("createdAt")))));

    }

    @PutMapping("/participar/projects-approve/project/{projectId}/invite/{inviteId}")
    public ResponseEntity<Participar> atualizaSolicitacoesDoProjeto(
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size,
            @PathVariable("projectId") String projectId,
            @PathVariable("inviteId") Long inviteId,
            @RequestBody Participar solicitacao

    ){
        return ResponseEntity.ok(
                participationConverter.convertToDto(
                        this.service.updateRequestsToMyProject(projectId,inviteId,solicitacao.getStatus())));
    }
}
