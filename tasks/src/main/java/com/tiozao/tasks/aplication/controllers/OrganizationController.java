package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.OrganizationDTO;
import com.tiozao.tasks.domain.entity.OrganizationEntity;
import com.tiozao.tasks.domain.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @GetMapping("/organizations")
    public ResponseEntity<List<OrganizationDTO>> listaOrganizations(Principal principal){
        return ResponseEntity.ok(
                service.getOrganizations(((JwtAuthenticationToken) principal).getToken().getClaim("sub"))
                        .stream()
                        .map(OrganizationController::convert)
                        .collect(Collectors.toList())
        );
    }


    @PostMapping("/organizations")
    @PreAuthorize("hasRole('OWNER_ROLE')")
    public ResponseEntity<String> listaOrganizations(@RequestBody OrganizationDTO organizationDTO, Principal principal){

        service.criarOrganizations(
                organizationDTO.getTitulo(),
                ((JwtAuthenticationToken) principal).getToken().getClaim("sub"));

        return ResponseEntity.ok(organizationDTO.getTitulo());
    }

    private static OrganizationDTO convert(OrganizationEntity entity){
        return new OrganizationDTO(entity.getName());
    }

}
