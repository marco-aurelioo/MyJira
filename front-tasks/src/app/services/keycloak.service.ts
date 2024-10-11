// src/app/services/keycloak.service.ts
import { Injectable } from '@angular/core';
import Keycloak, { KeycloakProfile } from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private keycloakInstance: Keycloak.KeycloakInstance;

  constructor() {
    this.keycloakInstance = new Keycloak({
      url: 'http://localhost:9090/',
      realm: 'DEV',
      clientId: 'task-track',
    });
  }

  init(): Promise<boolean> {
    return this.keycloakInstance.init({ onLoad: 'check-sso' }).then(authenticated => {
      console.log('Authenticated:', authenticated);
      console.log('Token:', this.keycloakInstance.token);
      if (!authenticated) {
        console.log('User is not authenticated, but no redirect to login');
      }
      return authenticated;
    }).catch(error => {
      console.error('Keycloak initialization failed', error);
      return false;
    });
  }
  

  isAuthenticated(): boolean {
    return !!this.keycloakInstance.token;
  }

  login(): void {
    this.keycloakInstance.login({ redirectUri: window.location.href });
  }

  logout(): void {
    this.keycloakInstance.logout();
  }

  getToken(): string | undefined {
    return this.keycloakInstance.token;
  }

  getKeycloakInstance(): Keycloak.KeycloakInstance {
    return this.keycloakInstance;
  }

  getUserName(): any {
    const keycloakInstance = this.keycloakInstance;
    if(keycloakInstance.tokenParsed){
      return keycloakInstance.tokenParsed["preferred_username"];
    }else{
      return "Sem UserName"
    }
  }

}
