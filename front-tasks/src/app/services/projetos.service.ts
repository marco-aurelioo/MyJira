import { Injectable } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projeto } from '../models/Projeto';
import { PageProjeto } from '../models/PageProjeto';

@Injectable({
  providedIn: 'root'
})
export class ProjetosService {


  private apiUrl: string = "http://localhost:8080/api/projects"

  constructor(private http: HttpClient, private keycloak: KeycloakService) { 
  }

  public criarProjeto(name: string, description: string, isPublic: boolean ): Observable<Projeto> {
     let headersValues = new HttpHeaders()
            .set('Authorization', 'Bearer ' + this.keycloak.getToken());
      
    return this.http.post<Projeto>(this.apiUrl,
          { name: name, 
            description: description,
            isPublic: isPublic
          },
          {headers: headersValues });
  }

  public findMyProjetos(page: Number, size: Number): Observable<PageProjeto>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<PageProjeto>(this.apiUrl+"/?page="+page+"&size="+size, {headers: headersValues });
  }

  public findProjetos(unicName: string): Observable<Projeto>{
    let headersValues = new HttpHeaders()
      .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.get<Projeto>(this.apiUrl+"/"+unicName, {headers: headersValues });
  }

}
