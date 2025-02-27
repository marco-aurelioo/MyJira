package com.tiozao.tasks.aplication.saas.userprofile;

import com.tiozao.tasks.aplication.saas.userprofile.model.SessionKeyCloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KeycloakClient  {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${app.config.keycloak.client_secret}")
    private String client_secret;
    @Value("${app.config.keycloak.client_id}")
    private String client_id;
    @Value("${app.config.keycloak.realm}")
    private String realm;
    @Value("${app.config.keycloak.domain}")
    private String keycloak_domain;


    public boolean addRole(String role, String userId) {
        Map<String,String> roleModel = findRole(role);
        return addRoleToClient(roleModel, userId);
    }

    private Map<String, String> findRole(String role) {
        String authorization = authenticate();

        HttpHeaders headersGet = new HttpHeaders();
        headersGet.setBearerAuth(authenticate());
        headersGet.setContentType(MediaType.APPLICATION_JSON);

        String url = keycloak_domain+"/admin/realms/"+realm+"/roles";
        HttpEntity<String> requestEntityGet = new HttpEntity<>(headersGet);
        ResponseEntity<List> roles = restTemplate.exchange( url, HttpMethod.GET, requestEntityGet, List.class);

        List<Map<String,String>> rolesMap = roles.getBody();

        Map<String,String> roleParaAtribuir = new HashMap<String,String>();
        for(Map<String,String> itemRole : rolesMap){
            if(role.equals(itemRole.get("name"))){
                return itemRole;
            }
        }
        throw new IllegalStateException("Role não identificada para o realm.");
    }


    private String authenticate(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", client_id);
        body.add("client_secret", client_secret);
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> retorno  = restTemplate.exchange(keycloak_domain+"/realms/"+realm+"/protocol/openid-connect/token",
                HttpMethod.POST, requestEntity,  Map.class );
        //podemos incluir um cache aqui
        return retorno.getBody().get("access_token").toString();
    }


    private boolean addRoleToClient(Map<String,String> role, String userId) {
        String postRole = keycloak_domain + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/realm";
        String authorizationToken = authenticate();
        HttpHeaders headersPostRole = new HttpHeaders();
        headersPostRole.setBearerAuth(authorizationToken);
        headersPostRole.setContentType(MediaType.APPLICATION_JSON);

        List<Map<String, Object>> rolesList = new ArrayList<>();
        Map<String, Object> roleToAssign = new HashMap<>();
        roleToAssign.put("id", role.get("id"));
        roleToAssign.put("name",  role.get("name"));
        rolesList.add(roleToAssign);
        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(rolesList, headersPostRole);

        ResponseEntity<Void> responseFinal = restTemplate.postForEntity(postRole, request, Void.class);

        return responseFinal.getStatusCode().equals(HttpStatus.NO_CONTENT);

    }

    public SessionKeyCloak findSessionKeyCloak(String tokenValue) {
        String postRole = keycloak_domain + "/realms/" + realm + "/protocol/openid-connect/token/introspect";
        HttpHeaders headersPostRole = new HttpHeaders();
        headersPostRole.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> introspect = new LinkedMultiValueMap<>();
        introspect.add("client_id", client_id);
        introspect.add("client_secret",  client_secret);
        introspect.add("token",  tokenValue);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(introspect, headersPostRole);

        ResponseEntity<SessionKeyCloak> responseFinal = restTemplate.postForEntity(postRole, request, SessionKeyCloak.class);

        return responseFinal.getBody();

    }
}
