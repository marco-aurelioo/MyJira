package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.PersonDto;
import com.tiozao.tasks.assembler.PersonConverter;
import com.tiozao.tasks.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(converter.convertDomain(
                service.findPerson(id)));
    }

    @PostMapping("/persons ")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto person) {
        return ResponseEntity.ok(
                converter.convertDomain(
                        service.createPerson(
                                converter.convertOrigin(person))));
    }
}
