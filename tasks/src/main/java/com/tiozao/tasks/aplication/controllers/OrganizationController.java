package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.OrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @PreAuthorize("hasRole('OWNER_ROLE')")
    public ResponseEntity<String> listaOrganizations(@RequestBody OrganizationDTO organizationDTO, Principal principal){

        return ResponseEntity.ok(organizationDTO.getOrganization());
    }

}
