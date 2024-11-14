import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { Projeto } from '../models/Projeto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjetosService {

  private apiUrl = 'http://localhost:8080/api/projeto';

  constructor(private http: HttpClient, private keycloak: KeycloakService) {}
  
  getProjetos(organizacao: string): Observable<Projeto[]> {
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Projeto[]>(this.apiUrl+"/"+organizacao, { "headers": headersValues} );
  }
  
  addProjeto(organizacao: string, projeto: Projeto): Observable<Projeto> {
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    console.log("Efetuando a chamada:"+organizacao+" "+projeto.nome);
    
    return this.http.post<Projeto>(this.apiUrl+"/"+organizacao, projeto,  {headers: headersValues });
  }

}
