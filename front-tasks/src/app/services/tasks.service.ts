import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';

import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class TasksService {
 
  constructor(private keycloakService: KeycloakService, private http: HttpClient) {
      this.token = keycloakService.getToken();
  }

  token: string | undefined;

  
  
}
