package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.Participar;
import com.tiozao.tasks.aplication.converter.ParticipationConverter;
import com.tiozao.tasks.services.ParticipationService;
import com.tiozao.tasks.services.ProjectRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ParticiparProjectController {

    @Autowired
    private ParticipationService service;

    @Autowired
    private ParticipationConverter converter;


    @GetMapping("/participar/projects-invites/")
    public ResponseEntity<Page<Participar>> buscaSolicitacoes(
            @RequestParam(name="page", required = false,defaultValue = "0") Integer page,
            @RequestParam(name="size", required = false,defaultValue = "20") Integer size,
            @RequestParam(name="termo", required = false,defaultValue = "") String termo
    ){

        return ResponseEntity.ok(
                service.findMyRequests(
                        termo,
                        PageRequest.of( page, size, Sort.by("createdAt")))
                        .map(converter::convertToDto));

    }



    @GetMapping("/participar/projects-invites/{projectId}")
    public ResponseEntity<String> buscaSolicitacoes(@PathVariable String projectId){
        //onde eu pedi para participar
        return ResponseEntity.ok("ok");
    }


    @PostMapping("/participar/projects-invites/{projectId}")
    public ResponseEntity<Participar> cadastrarSolicitacao(
            @PathVariable("projectId") String projectId,
            @RequestBody Participar participar
    ){
        return ResponseEntity.ok(
                converter.convertToDto(
                        service.createRequestToProject(
                                converter.convertToEntity(participar))));
    }

    @DeleteMapping("/participar/{participarId}")
    public ResponseEntity<String> cancelarParticipacao(
            @PathVariable("participarId") Long participarId
    ){
        if(service.cancelRequest(participarId)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
