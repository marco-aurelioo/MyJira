import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(
    private keycloakService: KeycloakService,
    private router: Router
  ) {}

  async canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    const requiredRoles = route.data['roles'] as string[];
    
    if (!this.keycloakService.isLoggedIn()) {
      await this.keycloakService.login();
      return false;
    }

    if (!requiredRoles || requiredRoles.length === 0) {
      return true;
    }

    const hasRole = requiredRoles.some(role => 
      this.keycloakService.isUserInRole(role)
    );

    if (!hasRole) {
      this.router.navigate(['/unauthorized']);
    }

    return hasRole;
  }
}