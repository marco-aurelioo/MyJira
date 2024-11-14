import { Injectable } from '@angular/core';
import { Organizacao } from '../models/Organizacao ';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { KeycloakService } from './keycloak.service';

@Injectable({
  providedIn: 'root'
})
export class OrganizacaoService {
  private apiUrl = 'http://localhost:8080/api/organizations';

  constructor(private http: HttpClient, private keycloak: KeycloakService) {}
  
  getOrganizacoes(): Observable<Organizacao[]> {
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Organizacao[]>(this.apiUrl , { "headers": headersValues} );
  }
  
  addOrganizacao(organizacao: Organizacao): Observable<Organizacao> {
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    console.log("Efetuando a chamada:"+organizacao.titulo);
    return this.http.post<Organizacao>(this.apiUrl, organizacao,  {headers: headersValues });
  }

}
