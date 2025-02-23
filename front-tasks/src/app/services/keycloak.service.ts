// src/app/services/keycloak.service.ts
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import Keycloak, { KeycloakProfile } from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private keycloakInstance: Keycloak.KeycloakInstance;


  constructor(private router: Router) {
    this.keycloakInstance = new Keycloak({
      url: 'http://192.168.15.17:9090/',
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
    
    const redirectUri = window.location.origin + window.location.pathname;
    this.keycloakInstance.login({ redirectUri });
    
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
