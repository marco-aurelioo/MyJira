import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PricesPlan } from '../models/PricesPlan';
import { KeycloakService } from './keycloak.service';
import { PlansSubmited } from '../models/PlansSubmited';

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
    
      let plansSubmited = new PlansSubmited(
        this.selectedPlan!.planName,
        this.selectedPlan!.price,
        "http://localhost:4200/payment-status?status=cancel&idrequest=",
        "http://localhost:4200/payment-status?status=success&idrequest="
      )
    
    return this.http.post('http://localhost:8080/api/submit/add-plan',  plansSubmited, {headers: headersValues });
  }

  getCheckoutPlan(idrequest: string, statusSended: string): Observable<any>{
    console.log("getCheckoutPlan");
    let headersValues = new HttpHeaders()
        .set('Authorization', 'Bearer ' + this.keycloak.getToken());
    
    let body = { status: statusSended }
    console.log("fazendo chamada !!!!!!!")
    return this.http.put('http://localhost:8080/api/submit/add-plan/'+idrequest,  body, {headers: headersValues });
  }

  checkPaymentStatus(): Observable<any> {
    return this.http.get('/api/payment-status');
  }

}
