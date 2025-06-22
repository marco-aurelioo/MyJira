import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { Observable } from 'rxjs';
import { Participar } from '../models/Participar';
import { PageParticipar } from '../models/PageParticipar';

@Injectable({
  providedIn: 'root'
})
export class ParticiparService {

  apiUrl: string = 'http://localhost:8080/api/participar/projects-invites';

  constructor(private http: HttpClient, private keycloak: KeycloakService) { }

  public enviarSolicitacao(solicitacao: Participar): Observable<Participar>{
    console.log("solicitacao");
    console.log(solicitacao);
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    
    console.log("enviadno solicitacao para projeto");

    return this.http.post<Participar>(
        this.apiUrl+"/"+solicitacao.projectId,
        solicitacao,
        { headers: headersValues });
  } 


  public buscarMinhasSolicitacoes(): Observable<PageParticipar> {
    
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    
    console.log("buscando minhas solicitacoes");

    return this.http.get<PageParticipar>(
        this.apiUrl+"/",
        { headers: headersValues });
  }

  public cancelarSolicitacaos(idSolicitacao: string): Observable<String> {
    
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    
    console.log("buscando minhas solicitacoes");

    return this.http.delete<String>(
        "http://localhost:8080/api/participar/"+idSolicitacao,
        { headers: headersValues });
  }

  public aceitarSolicitacao(solicitacao: Participar, acao :string):  Observable<String>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
  
    console.log("atualizar minhas solicitacoes");

    return this.http.put<String>(
        "http://localhost:8080/api/participar/",
        { headers: headersValues });
  }

}


