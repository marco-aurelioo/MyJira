import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AppAuthGuard extends KeycloakAuthGuard {
  constructor(protected override router: Router, protected override keycloakAngular: KeycloakService) {
    super(router, keycloakAngular);
  }

  public async isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    console.log("log:"+this.authenticated);
    if (!this.authenticated) {
      await this.keycloakAngular.login({
        redirectUri: window.location.origin + state.url
      });
      return false;
    }

    const requiredRoles = route.data['roles'];
    if (!requiredRoles || requiredRoles.length === 0) {
      return true;
    } else if (!this.roles || this.roles.length === 0) {
      return false;
    }

    return this.authenticated;// requiredRoles.every((role: string) => this.roles.includes(role));
  }
}
