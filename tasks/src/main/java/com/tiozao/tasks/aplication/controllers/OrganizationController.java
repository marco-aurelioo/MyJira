package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.OrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrganizationController {

    @GetMapping("/organizations")
    public List<String> listaOrganizations(){
        return new ArrayList<>();
    }


    @PostMapping("/organizations")
    public ResponseEntity<String> listaOrganizations(OrganizationDTO organizationDTO){
        return ResponseEntity.ok(organizationDTO.getOrganization());
    }

}
