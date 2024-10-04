package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.request.PersonRequest;
import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.assembler.PersonConverter;
import com.tiozao.tasks.assembler.models.PersonInputs;
import com.tiozao.tasks.assembler.models.PersonOutputs;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
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

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") Integer id) {
        throw new UnsupportedOperationException();
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
