import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { Tarefa } from '../models/Tarefa';
import { Observable } from 'rxjs';
import { PaginaTarefa } from '../models/PaginaTarefa';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private keycloak: KeycloakService) { 

  }

  apiUrl: string = "http://localhost:8080/api/project/";

  public createTarefa(projectName: string, tarefa :Tarefa ) :Observable<Tarefa> {
    let headersValues = new HttpHeaders()
            .set('Authorization', 'Bearer ' + this.keycloak.getToken());
            console.log("recebendo tarefa:");
            console.log( this.apiUrl+projectName+"/tasks");
            console.log("tarefa:"+tarefa);
    return this.http.post<Tarefa>(
      this.apiUrl+projectName+"/tasks",
      tarefa,
      {headers: headersValues });
  }

  public updateTarefa(projectName: string, tarefa :Tarefa ) :Observable<Tarefa> {
    let headersValues = new HttpHeaders()
            .set('Authorization', 'Bearer ' + this.keycloak.getToken());
            console.log("recebendo tarefa:");
            console.log( this.apiUrl+projectName+"/tasks/");
            console.log("tarefa:"+tarefa);
    return this.http.put<Tarefa>(
      this.apiUrl+projectName+"/tasks/"+tarefa.id,
      tarefa,
      {headers: headersValues });
  }

  public buscaTarefasProjeto(projectName: string,page: number, size: number): Observable<PaginaTarefa> {
    let headersValues = new HttpHeaders()
            .set('Authorization', 'Bearer ' + this.keycloak.getToken());
            console.log("recebendo tarefa:");
            console.log( this.apiUrl+projectName+"/tasks");
            
    return this.http.get<PaginaTarefa>(this.apiUrl+projectName+"/tasks", {headers: headersValues });
  }


}
