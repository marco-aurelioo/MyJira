package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.UserProfile;
import com.tiozao.tasks.aplication.converter.UserProfileConverter;
import com.tiozao.tasks.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PublicUserProfileController {


    @Autowired
    private UserProfileService service;

    @Autowired
    private UserProfileConverter converter;

    @GetMapping("/public/users/{userId}")
    public ResponseEntity<UserProfile> findProfileByExternalId(
            @PathVariable String userId){
        return  ResponseEntity.ok( converter.convertToDto(service.findUserByExternalId(userId)));
    }

    @GetMapping("/public/users/")
    public ResponseEntity<Page<UserProfile>> findProfile(
            @RequestParam(name="page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
            @RequestParam(name = "name", defaultValue = "", required = false) String name){
        return  ResponseEntity.ok(
                service.findByUserNameLike(name, PageRequest.of(page, size, Sort.by("name")))
                        .map(converter::convertToDto) );
    }
}
