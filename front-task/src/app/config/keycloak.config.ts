import { KeycloakConfig } from 'keycloak-js';

export const keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:9090', 
  realm: 'DEV',
  clientId: 'task-track'
};

export const keycloakInitOptions = {
  onLoad: 'check-sso' as const,
  checkLoginIframe: false,
  flow: 'standard' as const,
  silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
};