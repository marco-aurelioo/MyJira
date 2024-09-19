package com.tiozao.tasks.aplication.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicController {

    Logger log = LoggerFactory.getLogger(PublicController.class);


    @GetMapping("/public")
    public String publicEndpoint() {
        log.info("Public endpoint");
        return "This is a public endpoint.";
    }

}
