import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-confirm-plan',
  templateUrl: './confirm-plan.component.html',
  styleUrls: ['./confirm-plan.component.css']
})
export class ConfirmPlanComponent  implements OnInit {

  selectedPlan: any;

  constructor(private paymentService: PaymentService, private router: Router) {}

  ngOnInit() {
    this.selectedPlan = this.paymentService.getSelectedPlan();
    if (!this.selectedPlan) {
      this.router.navigate(['/precos']);
    }
  }

  confirmPurchase() {
    this.paymentService.submitPlan().subscribe((response: any) => {
      console.log("response "+response)
      console.log("paymentUrl "+response.urtPaymentMethod)
      if (response && response.urtPaymentMethod) {
        window.location.href = response.urtPaymentMethod;
      }
    });
  }



}
