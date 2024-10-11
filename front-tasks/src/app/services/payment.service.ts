import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PricesPlan } from '../models/PricesPlan';
import { KeycloakService } from './keycloak.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private selectedPlan?: PricesPlan;

  constructor(private http: HttpClient, private keycloak: KeycloakService) { }

  paySelectedPlan(pricePlan: PricesPlan){
    console.log("Server enviando plano:"+pricePlan)
    this.selectedPlan = pricePlan;
  }

  setSelectedPlan(plan: any) {
    this.selectedPlan = plan;
  }

  getSelectedPlan() {
    return this.selectedPlan;
  }

  submitPlan(): Observable<any> {
    console.log("enviando chamada para backend");
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    return this.http.post('http://localhost:8080/api/submit/add-plan',  this.selectedPlan, {headers: headersValues });
  }

  checkPaymentStatus(): Observable<any> {
    return this.http.get('/api/payment-status');
  }

}
