package com.tiozao.tasks.aplication.saas.userprofile;

import com.tiozao.tasks.aplication.saas.userprofile.model.SessionKeyCloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class KeycloakService {

    @Autowired
    private KeycloakClient keycloakClient;

    public SessionKeyCloak getSessionKeyCloak() {
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return keycloakClient.findSessionKeyCloak(authentication.getToken().getTokenValue());
    }
}
