import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlansSubmited } from 'src/app/models/PlansSubmited';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-payment-status',
  templateUrl: './payment-status.component.html',
  styleUrls: ['./payment-status.component.css']
})
export class PaymentStatusComponent {

  status: string = "";
  idrequest: string = "";
  plano?: PlansSubmited;

  constructor(private route: ActivatedRoute, private paymentService: PaymentService) {
    console.log('Called Constructor');
    this.route.queryParams.subscribe(params => {
      this.status = params['status'];
      this.idrequest = params['idrequest'];
   
    paymentService.getCheckoutPlan(this.idrequest, this.status).subscribe( (response: any) => {
      console.log('Called backe end');
      if (response) {
        this.plano = new PlansSubmited(response.planName,response.price,"","");
      }else{
       console.log("Erro ao recuperar plano do pedido.") 
      }
    }); }); 
  }



  


}
