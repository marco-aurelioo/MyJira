import { Injectable } from '@angular/core';
import { Participar } from '../models/Participar';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PageParticipar } from '../models/PageParticipar';
import { Projeto } from '../models/Projeto';
import { PageProjetoInvites } from '../models/PageProjetoInvites';

@Injectable({
  providedIn: 'root'
})
export class ParticiparProjetosService {

  constructor(private http: HttpClient) {}

  apiUrl: string = 'http://localhost:8080/api/participar';

  public enviarSolicitacao(solicitacao: Participar): Observable<Participar>{
    console.log("solicitacao");
    return this.http.post<Participar>(
        this.apiUrl+"/projects-invites/"+solicitacao.projectId,
        solicitacao);
  } 


  public buscarMinhasSolicitacoes(): Observable<PageParticipar> {
    console.log("buscando minhas solicitacoes");
    return this.http.get<PageParticipar>(
        this.apiUrl+"/projects-invites/" );
  }

  public cancelarSolicitacaos(idSolicitacao: string): Observable<String> {
    console.log("cancelado minha solicitacao");
    return this.http.delete<String>(
        this.apiUrl+"/projects-invites/"+idSolicitacao);
  }

  public findMyProjetosParaAprovar(paginaAtual:number, tamanhoPagina:number, termoBusca: string): Observable<PageProjetoInvites>{
    return this.http.get<PageProjetoInvites>( this.apiUrl+"/projects-approve/?page="+paginaAtual+"&size="+tamanhoPagina+"&termo="+termoBusca);
  }


  public findParticipacoes(project: Projeto, page: number, size: number):Observable<PageParticipar> {
    return this.http.get<PageParticipar>( this.apiUrl+"/projects-approve/project/"+project.projectId+"?page="+page+"&size="+size);
  }
  

  
  public atualizaPArticipacoes(solicitacao: Participar, acao :string):  Observable<String>{
    solicitacao.status = acao;
    console.log("atualizar minhas solicitacoes");
  return this.http.put<String>(
         this.apiUrl+"/projects-approve/project/"+solicitacao.projectId+"/invite/"+solicitacao.id,
        solicitacao);
  }
}
