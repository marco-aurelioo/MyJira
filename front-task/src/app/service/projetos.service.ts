import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProfileService } from './profile.service';
import { PageProjeto } from '../models/PageProjeto';
import { Observable } from 'rxjs';
import { Projeto } from '../models/Projeto';

@Injectable({
  providedIn: 'root'
})
export class ProjetosService {
  
private apiUrl: string = "http://localhost:8080/api/projects";

  constructor(private http: HttpClient, private profileService: ProfileService) { }

  public findMyProjetos(page: Number, size: Number): Observable<PageProjeto>{
    //talvez validar se o usuario esta logado
    return this.http.get<PageProjeto>(this.apiUrl+"/?page="+page+"&size="+size );
  }

  public findMyProjetosQueSouMembro(page: Number, size: Number): Observable<PageProjeto>{
    return this.http.get<PageProjeto>(this.apiUrl+"/member/?page="+page+"&size="+size );
  }

  public criarProjeto(name: string, description: string, isPublic: boolean ): Observable<Projeto> {
    console.log("recebendo projeto:"+isPublic);
    return this.http.post<Projeto>(this.apiUrl,
          { name: name, 
            description: description,
            public: isPublic
          });
  }

   public findProjetos( unicName: string): Observable<Projeto>{
    return this.http.get<Projeto>(this.apiUrl+"/"+unicName );
  }

}
