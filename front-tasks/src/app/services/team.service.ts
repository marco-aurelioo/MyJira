import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { Observable } from 'rxjs';
import { Profile } from '../models/Profile';
import { Projeto } from '../models/Projeto';

@Injectable({
  providedIn: 'root'
})
export class TeamService {


  urlApi: string = "http://localhost:8080/api/team";

  constructor(private http: HttpClient, private keycloak: KeycloakService) { 

  }

  createInvite(pessoaId: string,organizationSelected: string, selectedProjectId: string): Observable<Profile> {
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.post<Profile>(
      this.urlApi+"/"+organizationSelected+"/projects/"+selectedProjectId+"/invites", 
      { name:'', 'pessoaId': pessoaId},  
      {headers: headersValues });
  }

  getMyInvites(): Observable<any>{
    
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Profile>(
      this.urlApi+"/my-invites",       
      {headers: headersValues });
    
  }

  confirmMyInvites(inviteId: string, inviteStatus: boolean): Observable<any>{
    let headersValues = new HttpHeaders()
    .set('Authorization', 'Bearer ' + this.keycloak.getToken());
  return this.http.post<Profile>(
    this.urlApi+"/my-invites/"+inviteId, 
    { 'accept': inviteStatus , 'msg':'muito obrigado!'},  
    {headers: headersValues });
    
  }

  getMyTeams(): Observable<any>{
    
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Projeto>(
      this.urlApi+"/my-projects",       
      {headers: headersValues });
    
  }

}
