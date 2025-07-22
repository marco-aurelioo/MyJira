// src/app/services/auth.service.ts
import { Injectable, signal, computed, inject, effect } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor(private keycloakService: KeycloakService) {}

  async init() {
    return this.keycloakService.init({
      config: {
        url: 'http://localhost:9090', 
        realm: 'seu-realm',
        clientId: 'seu-client-id'
      },
      initOptions: {
        onLoad: 'login-required', // ou 'check-sso'
        silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html'
      }
    });
  }

  getToken(): Promise<string> | undefined {
    return this.keycloakService.getToken();
  }

  isLoggedIn(): boolean {
    return this.keycloakService.isLoggedIn();
  }

  getUserRoles(): string[] {
    return this.keycloakService.getUserRoles();
  }

  getUsername(): string | undefined {
    return this.keycloakService.getUsername();
  }

  logout(): void {
    this.keycloakService.logout();
  }

  login(): void {
    this.keycloakService.login();
  }

  hasRole(role: string): boolean {
    return this.keycloakService.isUserInRole(role);
  }
}