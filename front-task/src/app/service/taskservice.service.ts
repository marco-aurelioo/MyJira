import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tarefa } from '../models/Tarefa';
import { Observable } from 'rxjs';
import { PaginaTarefa } from '../models/PaginaTarefa';

@Injectable({
  providedIn: 'root'
})
export class TaskserviceService {
  
  constructor(private http: HttpClient) {  }

  apiUrl: string = "http://localhost:8080/api/project/";

  public createTarefa(projectName: string, tarefa :Tarefa ) :Observable<Tarefa> {
    return this.http.post<Tarefa>(
      this.apiUrl+projectName+"/tasks",
      tarefa);
  }

  public updateTarefa(projectName: string, tarefa :Tarefa ) :Observable<Tarefa> {
    return this.http.put<Tarefa>(
      this.apiUrl+projectName+"/tasks/"+tarefa.id,
      tarefa);
  }

   public buscaTarefasProjeto(projectName: string,page: number, size: number): Observable<PaginaTarefa> {
    return this.http.get<PaginaTarefa>(this.apiUrl+projectName+"/tasks");
  }

}
