import { Component, OnInit } from '@angular/core';
import { PricesPlan } from 'src/app/models/PricesPlan';
import { PricesService } from 'src/app/services/prices.service';

@Component({
  selector: 'app-precos',
  templateUrl: './precos.component.html',
  styleUrls: ['./precos.component.css']
})
export class PrecosComponent implements OnInit {

  plans?: PricesPlan[];
  
  constructor(private prices: PricesService){}

  ngOnInit(): void {
    console.log('Buscando planos ###############################################');
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
