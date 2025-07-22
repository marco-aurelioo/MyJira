import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projeto } from '../models/Projeto';
import { PageProjeto } from '../models/PageProjeto';
import { PageProjetoInvites } from '../models/PageProjetoInvites';
import { PageParticipar } from '../models/PageParticipar';
import { Participar } from '../models/Participar';

@Injectable({
  providedIn: 'root'
})
export class ProjetosService {


  private apiUrl: string = "http://localhost:8080/api/projects";
  private publicApiUrl: string = "http://localhost:8080/api/public/projects";

  constructor(private http: HttpClient, private keycloak: KeycloakService) { 
  }

  public criarProjeto(name: string, description: string, isPublic: boolean ): Observable<Projeto> {
    let headersValues = new HttpHeaders()
            .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    console.log("recebendo projeto:"+isPublic);
    return this.http.post<Projeto>(this.apiUrl,
          { name: name, 
            description: description,
            public: isPublic
          },
          {headers: headersValues });
  }

  public findMyProjetos(page: Number, size: Number): Observable<PageProjeto>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PageProjeto>(this.apiUrl+"/?page="+page+"&size="+size, {headers: headersValues });
  }

  public findMyProjetosQueSouMembro(page: Number, size: Number): Observable<PageProjeto>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PageProjeto>(this.apiUrl+"/member/?page="+page+"&size="+size, {headers: headersValues });
  }

  public findProjetos( unicName: string): Observable<Projeto>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Projeto>(this.apiUrl+"/"+unicName, {headers: headersValues });
  }

  public findPublicProjetos(paginaAtual:number, tamanhoPagina:number, termoBusca: string): Observable<PageProjeto>{
    console.log(this.publicApiUrl+"/?page="+paginaAtual+"&size="+tamanhoPagina+"&termo="+termoBusca);

    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PageProjeto>(this.publicApiUrl+"/?page="+paginaAtual+"&size="+tamanhoPagina+"&termo="+termoBusca, {headers: headersValues });
  }


  public findMyProjetosParaAprovar(paginaAtual:number, tamanhoPagina:number, termoBusca: string): Observable<PageProjetoInvites>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PageProjetoInvites>("http://localhost:8080/api/participar/projects-approve/?page="+paginaAtual+"&size="+tamanhoPagina+"&termo="+termoBusca, {headers: headersValues });
  }


  public findParticipacoes(project: Projeto, page: number, size: number):Observable<PageParticipar> {
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PageParticipar>("http://localhost:8080/api/participar/projects-approve/project/"+project.projectId+"?page="+page+"&size="+size, {headers: headersValues });
  }

  public atualizaPArticipacoes(solicitacao: Participar, acao :string):  Observable<String>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    solicitacao.status = acao;
    console.log("atualizar minhas solicitacoes");

    return this.http.put<String>(
        "http://localhost:8080/api/participar/projects-approve/project/"+solicitacao.projectId+"/invite/"+solicitacao.id,
        solicitacao);
  }

}
