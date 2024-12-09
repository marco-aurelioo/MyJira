package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.ProfileDTO;
import com.tiozao.tasks.domain.entity.PersonEntity;
import com.tiozao.tasks.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private PersonService personService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile(Principal principal){
        try {
            PersonEntity entity = personService.findPersonByUserId(((JwtAuthenticationToken) principal).getToken().getClaim("sub"));
            return ResponseEntity.ok(getProfileDTO(entity));
        }catch (IllegalStateException ex){
            PersonEntity entity = new PersonEntity();
            entity.setAvatar(null);
            String sub = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
            entity.setUserId(sub);
            entity.setName(principal.getName());
            entity = personService.createPerson(entity);
            return ResponseEntity.ok(getProfileDTO(entity));
        }
    }




    private ProfileDTO getProfileDTO(PersonEntity entity) {
        ProfileDTO profile = new ProfileDTO();
        profile.setUsername(entity.getName());
        profile.setUserId(entity.getUserId());
        profile.setImage(entity.getAvatar());
        return profile;
    }
}
