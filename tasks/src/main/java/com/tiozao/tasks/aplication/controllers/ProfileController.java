package com.tiozao.tasks.aplication.controllers;

import com.tiozao.tasks.aplication.dtos.ProfileDTO;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfileDTO(Principal principal){
        ProfileDTO profile = new ProfileDTO();
        String sub = ((JwtAuthenticationToken) principal).getToken().getClaim("sub");
        profile.setUsername(principal.getName());
        profile.setUserId(sub);
        return ResponseEntity.ok(profile);
    }
}
