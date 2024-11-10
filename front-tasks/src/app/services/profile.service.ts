import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from '../models/Profile';
import { KeycloakService } from './keycloak.service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private apiUrl = 'http://localhost:8080/api/profile';
  
  constructor(private http: HttpClient, private keycloak: KeycloakService) { 

  }

  getProfile(): Observable<Profile> {
    console.log("chegou a entrar na consulta");
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Profile>(this.apiUrl, {headers: headersValues });
  } 

}
