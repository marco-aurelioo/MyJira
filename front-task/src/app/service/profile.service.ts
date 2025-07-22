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
    if (!this.isAuthenticate()) {
      throw new Error('Usuário não autenticado');
    }
    return this.http.get<Profile>(this.apiUrl+"/my-profile");
  } 

  salvaProfile(userId: string, name: string, avatar: string): Observable<Profile> {
    if (!this.isAuthenticate()) {
      throw new Error('Usuário não autenticado');
    }
    return this.http.put<Profile>(this.apiUrl+"/"+userId,
      { userId: userId, name: name, avatar: avatar });
  }

  login(){
    this.auth.login();
  }

  logout(){
    this.auth.logout();
  }
  
  
}
