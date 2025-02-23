package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.controllers.model.UserProfile;
import com.tiozao.tasks.aplication.converter.UserProfileConverter;
import com.tiozao.tasks.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PrivateUserController {

    @Autowired
    private UserProfileConverter converter;

    @Autowired
    private UserProfileService service;

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserProfile> updateMe(
            @PathVariable String userId,
            @RequestBody UserProfile userProfile){
        return ResponseEntity.ok(converter.convertToDto(
                service.updateProfile(UUID.fromString(userId),
                        converter.convertToEntity(userProfile))
        ));
    }

    @GetMapping("/users/my-profile")
    public ResponseEntity<UserProfile> getMyProfile(){
        return  ResponseEntity.ok(converter.convertToDto(
                service.findMyUserProfile()
        ));
    }

}
