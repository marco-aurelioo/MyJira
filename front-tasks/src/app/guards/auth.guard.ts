// src/app/guards/auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { KeycloakService } from '../services/keycloak.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private keycloakService: KeycloakService, private router: Router) {}

  canActivate(): boolean {    
    console.log(this.keycloakService.isAuthenticated());
    if (this.keycloakService.isAuthenticated()) {
      return true;
    } else {
      
      this.keycloakService.login(); 
      return false;
    }
    
  }
}
