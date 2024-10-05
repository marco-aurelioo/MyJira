import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PricesPlan } from '../models/PricesPlan';

@Injectable({
  providedIn: 'root'
})
export class PricesService {

  private apiUrl = 'http://localhost:8080/api/public/subscriptions';
  
  constructor(private http: HttpClient) { 
    

  }

  getPrices(): Observable<PricesPlan[]> {
    console.log("chegou a entrar na consulta");
    return this.http.get<PricesPlan[]>(this.apiUrl);
  }


}
