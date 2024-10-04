package com.tiozao.tasks.config;

import com.tiozao.tasks.domain.service.providers.KeycloakClient;
import com.tiozao.tasks.domain.service.providers.PermissionApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PermissionApi getPermissionApi(
            RestTemplate restTemplate,
            @Value("{app.config.keycloak.client_secret}") String client_secret,
            @Value("{app.config.keycloak.client_id}") String client_id,
            @Value("{app.config.keycloak.realm}") String realm,
            @Value("{app.config.keycloak.domain}") String keycloak_domain) {
        return new KeycloakClient(restTemplate, client_secret, client_id, realm, keycloak_domain);
    }

}
