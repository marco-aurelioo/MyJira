package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.PersonRequest;
import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.assembler.converters.PersonConverter;
import com.tiozao.tasks.assembler.converters.PersonModelConverter;
import com.tiozao.tasks.assembler.converters.models.PersonInputs;
import com.tiozao.tasks.assembler.converters.models.PersonModelIn;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    @Autowired
    private PersonModelConverter converterModel;

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") Integer id) {
        throw new UnsupportedOperationException();
    }


    @GetMapping("/persons/")
    public ResponseEntity<Page<PersonDto>> findPerson(
            @RequestParam(name = "name") Integer nameLike,
            Pageable pageable
    ) {
       return ResponseEntity.ok(
               converterModel.createPageFromEntities(
                       service.findPersonLikeName(nameLike,pageable)));
    }


    @PostMapping("/persons")
    public ResponseEntity<PersonEntity> createPerson(
            Principal principal,
            @RequestBody PersonRequest person) {
        return ResponseEntity.ok(
                        service.createPerson(
                                converter.convertOrigin(
                                        new PersonInputs(person,principal)
                                ).getEntity()
                        ));
    }

}
