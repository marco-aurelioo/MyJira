import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Profile } from '../models/Profile';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  
  constructor(private http: HttpClient, private auth: AuthService) { 

  }

  isAuthenticate(): boolean {
    return this.auth.isLoggedIn();
  }

  private apiUrl = 'http://localhost:8080/api/users';
  
  getProfile(): Observable<Profile> {
    console.log("chegou a entrar na consulta");
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.auth.getToken());
    return this.http.get<Profile>(this.apiUrl+"/my-profile", {headers: headersValues });
  } 

  login(){
    this.auth.login();
  }

  logout(){
    this.auth.logout();
  }
  
  
}
