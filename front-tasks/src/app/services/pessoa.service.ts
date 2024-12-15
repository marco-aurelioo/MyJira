import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from '../models/Profile';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { KeycloakService } from './keycloak.service';
import { PagePessoa } from '../models/PagePessoa';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  apiUrl: string = 'http://localhost:8080/api/persons';

  constructor(private http: HttpClient, private keycloak: KeycloakService) { }

  getPaginaPessoa(searchTerm: string, toInviteProject: string, currentPage: any, pageSize: any): Observable<PagePessoa> {
    console.log("chegou a entrar na consulta");
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PagePessoa>(this.apiUrl+"?size="+pageSize+"&page="+currentPage+"&name="+searchTerm+"&toInviteProject="+toInviteProject, {headers: headersValues });
  } 

}
