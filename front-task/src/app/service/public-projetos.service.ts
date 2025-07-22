import { Injectable } from '@angular/core';
import { ProfileService } from './profile.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PageProjeto } from '../models/PageProjeto';

@Injectable({
  providedIn: 'root'
})
export class PublicProjetosService {
  
   constructor(private http: HttpClient) { }

   private publicApiUrl: string = "http://localhost:8080/api/public/projects";

   public findPublicProjetos(paginaAtual:number, tamanhoPagina:number, termoBusca: string): Observable<PageProjeto>{
    
    console.log(this.publicApiUrl+"/?page="+paginaAtual+"&size="+tamanhoPagina+"&termo="+termoBusca);
    return this.http.get<PageProjeto>(this.publicApiUrl+"/?page="+paginaAtual+"&size="+tamanhoPagina+"&termo="+termoBusca);

  }

}
