import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PricesPlan } from 'src/app/models/PricesPlan';
import { PaymentService } from 'src/app/services/payment.service';
import { PricesService } from 'src/app/services/prices.service';

@Component({
  selector: 'app-precos',
  templateUrl: './precos.component.html',
  styleUrls: ['./precos.component.css']
})
export class PrecosComponent implements OnInit {

  plans?: PricesPlan[];
  

  constructor(
    private prices: PricesService,
    private payment: PaymentService,
    private router: Router
  ){}

  selectPlan(sendPlanName: string ){
    console.log("plano selecionado "+sendPlanName)
    var plan = this.plans!.find(x => x.planName === sendPlanName )
    if(plan){
      this.payment.paySelectedPlan(plan);
      this.router.navigate(['/confirm-plan']).then(success => {
        if (!success) {
          console.error('Falha no redirecionamento para /confirm-plan');
        }
      });
    }
  }

  ngOnInit(): void {
    this.prices.getPrices().subscribe(
      response => {
        this.plans = response;
        console.log('Dados recebidos:', this.plans);
      },
      error => {
        console.error('Erro ao carregar dados:', error);
      }
      
    );
  }
}
